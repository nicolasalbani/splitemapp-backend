package com.splitemapp.service.backendrest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.utils.BackendUtils;
import com.splitemapp.domainmodel.domain.User;
import com.splitemapp.service.domainmodel.dto.LoginRequest;
import com.splitemapp.service.domainmodel.dto.LoginResponse;

@Service
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginService {

	private UserEndpoint userEndpoint;

	@POST
	public LoginResponse printMessage(LoginRequest request) {

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setSuccess(false);

		User user = userEndpoint.findByUsername(request.getUsername());

		// If we found the user then we validate
		if(user!=null){
			// If password matches we return a new session
			String requestHashedPassword = BackendUtils.hashPassword(request.getPassword());
			if(user.getPassword().equals(requestHashedPassword)){
				loginResponse.setSuccess(true);
				loginResponse.setSessionToken(BackendUtils.createSessionToken());
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
}