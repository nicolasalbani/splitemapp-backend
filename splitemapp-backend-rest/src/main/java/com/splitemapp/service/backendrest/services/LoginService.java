package com.splitemapp.service.backendrest.services;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserAvatar;
import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.UserSessionDTO;
import com.splitemapp.commons.domain.dto.UserStatusDTO;
import com.splitemapp.commons.domain.dto.request.LoginRequest;
import com.splitemapp.commons.domain.dto.response.LoginResponse;
import com.splitemapp.commons.utils.TimeUtils;
import com.splitemapp.service.backendrest.endpoint.UserAvatarEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserContactDataEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;
import com.splitemapp.service.backendrest.utils.BackendUtils;

@Service
@Path(ServiceConstants.LOGIN_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginService {

	private UserEndpoint userEndpoint;
	private UserAvatarEndpoint userAvatarEndpoint;
	private UserContactDataEndpoint userContactDataEndpoint;
	private UserSessionEndpoint userSessionEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public LoginResponse printMessage(LoginRequest request) {

		// We create a login response object setting success to false by default
		LoginResponse response = new LoginResponse();

		// We look for the user
		User user = userEndpoint.findUserForLogin(TableField.USER_USERNAME, request.getUsername());

		// If we found the user then we validate
		if(user!=null){
			// If password matches we return a new session
			if(user.getPassword().equals(request.getPassword())){
				// We generate a new session token
				String sessionToken = BackendUtils.createSessionToken();

				// We write in the user_session table
				UserSession userSession = new UserSession();
				userSession.setDevice(request.getDevice());
				userSession.setOsVersion(request.getOsVersion());
				userSession.setLastUsedAt(TimeUtils.getUTCDate());
				userSession.setToken(sessionToken);
				userSession.setUser(user);
				userSessionEndpoint.persist(userSession);

				// We update the last login time and login count for the user
				user.setLastLogin(TimeUtils.getUTCDate());
				user.setLoginCnt(user.getLoginCnt()+1);
				userEndpoint.merge(user);

				// We get the existing user contact data from the user
				UserContactData userContactData = null;
				Set<UserContactData> userContactDatas = user.getUserContactDatas();
				for(UserContactData ucd:userContactDatas){
					userContactData = ucd;
				}
				
				// Getting the existing user avatar from the user
				UserAvatar userAvatar = null;
				Set<UserAvatar> userAvatars = user.getUserAvatars();
				for(UserAvatar ua:userAvatars){
					userAvatar = ua;
				}

				// We generate the response
				response.setSuccess(true);
				response.setUserSessionDTO(new UserSessionDTO(userSession));
				response.setUserDTO(new UserDTO(userSession.getUser()));
				response.setUserStatusDTO(new UserStatusDTO(userSession.getUser().getUserStatus()));
				response.setUserContactDataDTO(new UserContactDataDTO(userContactData));
				response.setUserAvatarDTO(new UserAvatarDTO(userAvatar));
				response.setChangePassword(false);
			} else {
				response.setMessage(ServiceConstants.ERROR_MESSAGE_LOGIN_FAILED);
			}
		} else {
			response.setMessage(ServiceConstants.ERROR_MESSAGE_LOGIN_FAILED);
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

	public UserContactDataEndpoint getUserContactDataEndpoint() {
		return userContactDataEndpoint;
	}

	public void setUserContactDataEndpoint(UserContactDataEndpoint userContactDataEndpoint) {
		this.userContactDataEndpoint = userContactDataEndpoint;
	}

	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}

	public UserAvatarEndpoint getUserAvatarEndpoint() {
		return userAvatarEndpoint;
	}

	public void setUserAvatarEndpoint(UserAvatarEndpoint userAvatarEndpoint) {
		this.userAvatarEndpoint = userAvatarEndpoint;
	}
}