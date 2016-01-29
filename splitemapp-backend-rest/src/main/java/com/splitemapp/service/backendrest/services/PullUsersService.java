package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.response.PullResponse;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PULL_USERS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullUsersService {

	UserSessionEndpoint userSessionEndpoint;
	UserEndpoint userEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PullResponse<UserDTO> printMessage(PullRequest request) throws ParseException {
		// We create a pull user response object setting success to false by default
		PullResponse<UserDTO> response = new PullResponse<UserDTO>();
		response.setSuccess(false);

		// Creating the pulledAt date
		Date pulledAt = new Date();

		// Retrieving user session
		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// Getting the logged user from the session
			User loggedUser = userSession.getUser();

			// Getting all users linked with the logged user through a project
			List<User> linkedUsers = userEndpoint.findPushedAfterByUser(request.getLastPullSuccessAt(), loggedUser.getId());

			// We set the users
			Set<UserDTO> userDTOs = new HashSet<UserDTO>();
			for(User user:linkedUsers){
				userDTOs.add(new UserDTO(user));
			}
			response.setItemSet(userDTOs);

			// We set the success flag
			response.setPulledAt(pulledAt);
			response.setSuccess(true);
		}

		return response;
	}


	// Getters and setters
	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}

	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}
}