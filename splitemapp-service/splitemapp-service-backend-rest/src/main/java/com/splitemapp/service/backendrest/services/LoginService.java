package com.splitemapp.service.backendrest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.domainmodel.domain.User;
import com.splitemapp.service.domainmodel.dto.request.LoginRequest;
import com.splitemapp.service.domainmodel.dto.response.LoginResponse;

@Service
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginService {
 
	private UserEndpoint userEndpoint;

	@POST
	public LoginResponse printMessage(LoginRequest request) {
		
		LoginResponse loginResponse = new LoginResponse();
		
		User user = userEndpoint.findByUsername(request.getUsername());
		
		if(user!=null){
			loginResponse.setUserId(user.getId());
			loginResponse.setFirstName(user.getFirstName());
			loginResponse.setLastName(user.getLastName());
			loginResponse.setSessionToken("new_session_token");
			loginResponse.setChangePassword(false);
			loginResponse.setIsNewDevice(true);
			loginResponse.setUsername(user.getUsername());
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