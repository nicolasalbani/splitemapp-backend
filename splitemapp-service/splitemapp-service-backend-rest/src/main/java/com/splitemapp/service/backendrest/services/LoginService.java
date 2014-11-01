package com.splitemapp.service.backendrest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.fairpay.domainmodel.domain.User;
import com.fairpay.domainmodel.dto.response.LoginResponse;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;

@Service
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class LoginService {
 
	private UserEndpoint userEndpoint;

	@GET
	public LoginResponse printMessage() {
 
		LoginResponse loginResponse = new LoginResponse();
		
		User user = userEndpoint.findById(1L);
		
		if(user!=null){
			loginResponse.setUserId(user.getId());
			loginResponse.setFirstName(user.getFirstName());
			loginResponse.setLastName(user.getLastName());
			loginResponse.setSessionToken("new_session_token");
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