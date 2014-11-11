package com.splitemapp.service.backendrest.services;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;
import com.splitemapp.service.backendrest.utils.BackendUtils;
import com.splitemapp.domainmodel.domain.User;
import com.splitemapp.domainmodel.domain.UserSession;
import com.splitemapp.service.domainmodel.dto.LoginRequest;
import com.splitemapp.service.domainmodel.dto.LoginResponse;

@Service
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginService {

	private UserEndpoint userEndpoint;
	private UserSessionEndpoint userSessionEndpoint;

	@POST
	public LoginResponse printMessage(LoginRequest request) {

		// We create a login response object setting success to false by default
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setSuccess(false);

		// We look for the user
		User user = userEndpoint.findByUsername(request.getUsername());

		// If we found the user then we validate
		if(user!=null){
			// If password matches we return a new session
			if(user.getPassword().equals(request.getPassword())){
				// We generate a new session token
				String sessionToken = BackendUtils.createSessionToken();
				
				// We write in the user_session table
				UserSession userSession = new UserSession();
				userSession.setDevice(request.getDevice());
				userSession.setLastUsedAt(new Date());
				userSession.setToken(sessionToken);
				userSession.setUser(user);
				userSessionEndpoint.persist(userSession);
				
				// We generate the response
				loginResponse.setSuccess(true);
				loginResponse.setSessionToken(sessionToken);
				loginResponse.setChangePassword(false);
			} 
		}

		return loginResponse;
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