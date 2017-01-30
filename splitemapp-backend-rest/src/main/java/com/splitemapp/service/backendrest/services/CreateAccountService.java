package com.splitemapp.service.backendrest.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.splitemapp.commons.rest.RestUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.constants.TableFieldCod;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserAvatar;
import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.commons.domain.UserStatus;
import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.UserStatusDTO;
import com.splitemapp.commons.domain.dto.request.CreateAccountRequest;
import com.splitemapp.commons.domain.dto.response.CreateAccountResponse;
import com.splitemapp.commons.utils.MailUtils;
import com.splitemapp.commons.utils.TimeUtils;
import com.splitemapp.service.backendrest.endpoint.UserAvatarEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserContactDataEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserStatusEndpoint;


@Service
@Path(ServiceConstants.CREATE_ACCOUNT_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateAccountService {

	private static Logger logger = Logger.getLogger(CreateAccountService.class);

	private UserStatusEndpoint userStatusEndpoint;
	private UserEndpoint userEndpoint;
	private UserAvatarEndpoint userAvatarEndpoint;
	private UserContactDataEndpoint userContactDataEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public CreateAccountResponse printMessage(CreateAccountRequest request) {
		// Service start time
		DateTime serviceStartTime = new DateTime();

		// We create a create account response object setting success to false by default
		CreateAccountResponse response = new CreateAccountResponse();

		// We check if the user already exists
		User user = userEndpoint.findByField(TableField.USER_USERNAME, request.getEmail());

		// If we did not find the user then we create it
		if(user==null){
			// We get the active user status
			UserStatus userStatus = userStatusEndpoint.findByField(TableField.ALTER_TABLE_COD, TableFieldCod.USER_STATUS_ACTIVE);

			// We create and persist the user instance
			User newUser = new User();
			newUser.setCreatedIpAddress(request.getIpAddress());
			newUser.setUpdatedAt(TimeUtils.getUTCDate());
			newUser.setUpdatedIpAddress(request.getIpAddress());
			newUser.setPassword(request.getPassword());
			newUser.setUsername(request.getEmail());
			newUser.setFullName(request.getFullName());
			newUser.setUserStatus(userStatus);
			newUser.setPushedAt(TimeUtils.getUTCDate());
			userEndpoint.persist(newUser);

			// We create and persist the user contact data
			UserContactData userContactData = new UserContactData();
			userContactData.setContactData(request.getEmail());
			userContactData.setUser(newUser);
			userContactData.setUpdatedBy(newUser);
			userContactData.setPushedAt(TimeUtils.getUTCDate());
			userContactData.setPushedBy(newUser);
			userContactDataEndpoint.persist(userContactData);

			// We get the avatar from facebook
			byte[] avatar = null;
			if(request.getAvatarUrl() != null){
				try {
					avatar = RestUtils.downloadImage(request.getAvatarUrl());
				} catch (IOException e) {
					logger.error("Error getting profile picture from Facebook", e);
				}
			}

			// We create and persist the user avatar
			UserAvatar userAvatar = new UserAvatar();
			userAvatar.setAvatarData(avatar);
			userAvatar.setUser(newUser);
			userAvatar.setPushedAt(TimeUtils.getUTCDate());
			userAvatar.setUpdatedBy(newUser);
			userAvatar.setPushedBy(newUser);
			userAvatarEndpoint.persist(userAvatar);

			// We set the response values
			response.setSuccess(true);
			response.setUserContactDataDTO(new UserContactDataDTO(userContactData));
			response.setUserDTO(new UserDTO(newUser, false));
			response.setUserAvatarDTO(new UserAvatarDTO(userAvatar));
			response.setUserStatusDTO(new UserStatusDTO(userStatus));
		} else {
			response.setMessage(ServiceConstants.ERROR_MESSAGE_ACCOUNT_EXISTS);
		}

		// If we successfully created the account, we send a welcome message
		if(response.getSuccess()){
			// Creating the map with the replacement
			Map<String,String> placeholdersMap = new HashMap<String,String>();
			placeholdersMap.put("SMTPHOST", ServiceConstants.SMTP_HOST);
			placeholdersMap.put("FULLNAME", request.getFullName());

			// Creating MailUtils object
			MailUtils mailUtils = new MailUtils();
			
			// Crafting email message
			String message = mailUtils.craftMailText("welcome.html", placeholdersMap);

			// Sending the question
			mailUtils.sendMail(ServiceConstants.INFO_SERVICE_ADDRESS,ServiceConstants.INFO_SERVICE_PASS,"Welcome to Splitemapp!", request.getEmail(), ServiceConstants.INFO_SERVICE_ADDRESS, message);
		}

		// Calculating service time
		logger.info(getClass().getSimpleName() +" time was: "+ (new DateTime().getMillis()-serviceStartTime.getMillis()+ "ms"));

		return response;
	}

	// Getters and setters

	public UserStatusEndpoint getUserStatusEndpoint() {
		return userStatusEndpoint;
	}

	public void setUserStatusEndpoint(UserStatusEndpoint userStatusEndpoint) {
		this.userStatusEndpoint = userStatusEndpoint;
	}

	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

	public UserContactDataEndpoint getUserContactDataEndpoint() {
		return userContactDataEndpoint;
	}

	public void setUserContactDataEndpoint(
			UserContactDataEndpoint userContactDataEndpoint) {
		this.userContactDataEndpoint = userContactDataEndpoint;
	}

	public UserAvatarEndpoint getUserAvatarEndpoint() {
		return userAvatarEndpoint;
	}

	public void setUserAvatarEndpoint(UserAvatarEndpoint userAvatarEndpoint) {
		this.userAvatarEndpoint = userAvatarEndpoint;
	}
}