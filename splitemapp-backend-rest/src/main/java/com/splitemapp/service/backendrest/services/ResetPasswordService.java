package com.splitemapp.service.backendrest.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.PasswordReset;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.dto.request.PasswordResetRequest;
import com.splitemapp.commons.domain.dto.response.PasswordResetResponse;
import com.splitemapp.commons.utils.MailUtils;
import com.splitemapp.commons.utils.RandomStringUtils;
import com.splitemapp.commons.utils.TimeUtils;
import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PASSWORD_RESET_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResetPasswordService {

	private static final int TEMP_CODE_LENGTH = 20;
	private static final int TEMP_PASSWORD_LENGTH = 8;
	private static final int PASSWORD_RESET_TIMEOUT = 30;

	private static Map<String,PasswordReset> passwordResetMap = new HashMap<String,PasswordReset>();

	private UserEndpoint userEndpoint;
	private UserSessionEndpoint userSessionEndpoint;

	@GET
	@Path("/{param}")
	public String printMessage(@PathParam("param") String temporaryKey) {
		// Checking if the temporary key exists in the map
		boolean containsKey = passwordResetMap.containsKey(temporaryKey);
		
		if(containsKey){
			// Obtaining and removing the password reset item
			PasswordReset passwordReset = passwordResetMap.remove(temporaryKey);
			
			// Checking if the password reset expired
			DateTime requestDate = passwordReset.getRequestDate().plusMinutes(PASSWORD_RESET_TIMEOUT);
			if(!TimeUtils.getUTCDateTime().isAfter(requestDate)){
				// We create a temporary password
				String tempPassword = RandomStringUtils.getRandomString(TEMP_PASSWORD_LENGTH);
				
				// We look for the user
				User user = userEndpoint.findById(passwordReset.getUserId());
				
				// We update the password for this user
				user.setPassword(Utils.hashPassword(tempPassword));
				
				// We persist the new password
				userEndpoint.merge(user);
				
				// We send an e-mail letting the user know the new password
				// Creating the map with the replacement
				Map<String,String> placeholdersMap = new HashMap<String,String>();
				placeholdersMap.put("FULLNAME", user.getFullName());
				placeholdersMap.put("NEWPASSWORD", tempPassword);

				// Crafting email message
				String message = MailUtils.craftMailText("password_reset_success.html", placeholdersMap);

				// Sending the question
				MailUtils.sendMail("info","019713skull","Password reset success", user.getUsername(), "info@splitemapp.com", message);
			}
		}
		
		return "Password reset code expired. Please request a new code";
	}

	@POST
	public PasswordResetResponse printMessage(PasswordResetRequest request) {
		// We create a response object setting emailFound to false by default
		PasswordResetResponse response = new PasswordResetResponse();
		response.setMailFound(false);

		// We look for the user
		User user = userEndpoint.findUserForLogin(TableField.USER_USERNAME, request.getUsername());

		// If we found the user then we validate
		if(user!=null){
			// Setting mail found to true
			response.setMailFound(true);

			// Creating temporary key
			String key = RandomStringUtils.getRandomString(TEMP_CODE_LENGTH);

			// Saving temporary key to the static MAP
			PasswordReset passwordReset = new PasswordReset(TimeUtils.getUTCDateTime(), user.getId());
			passwordResetMap.put(key, passwordReset);
			
			// Sending e-mail to user with link to reset password
			// Creating the map with the replacement
			Map<String,String> placeholdersMap = new HashMap<String,String>();
			placeholdersMap.put("FULLNAME", user.getFullName());
			placeholdersMap.put("SERVICEURL", ServiceConstants.BACKEND_HOST+":"+ServiceConstants.BACKEND_PORT+"/"+ServiceConstants.BACKEND_PATH+"/"+ServiceConstants.PASSWORD_RESET_PATH+"/"+key);

			// Crafting email message
			String message = MailUtils.craftMailText("password_reset_request.html", placeholdersMap);

			// Sending the question
			MailUtils.sendMail("info","019713skull","Password reset request", request.getUsername(), "info@splitemapp.com", message);
		} else {
			response.setMessage(ServiceConstants.ERROR_MESSAGE_ACCOUNT_NOT_FOUND);
		}

		return response;
	}

	//Getters and setters
	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}
}