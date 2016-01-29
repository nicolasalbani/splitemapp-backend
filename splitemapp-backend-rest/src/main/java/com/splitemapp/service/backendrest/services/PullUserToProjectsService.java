package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
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
import com.splitemapp.commons.domain.UserToProject;
import com.splitemapp.commons.domain.dto.UserToProjectDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.response.PullResponse;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserToProjectEndpoint;

@Service
@Path(ServiceConstants.PULL_USER_TO_PROJECTS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullUserToProjectsService {

	UserSessionEndpoint userSessionEndpoint;
	UserToProjectEndpoint userToProjectEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PullResponse<UserToProjectDTO> printMessage(PullRequest request) throws ParseException {
		// We create a pull all sync response object setting success to false by default
		PullResponse<UserToProjectDTO> response = new PullResponse<UserToProjectDTO>();
		response.setSuccess(false);

		// Creating the pulledAt date
		Date pulledAt = new Date();

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			User user = userSession.getUser();

			// We set the user to project set
			Set<UserToProjectDTO> userToProjectDTOs = new HashSet<UserToProjectDTO>();
			for(UserToProject userToProject:userToProjectEndpoint.findPushedAfterByUser(request.getLastPullSuccessAt(), user.getId())){
				userToProjectDTOs.add(new UserToProjectDTO(userToProject));
			}
			response.setItemSet(userToProjectDTOs);

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

	public UserToProjectEndpoint getUserToProjectEndpoint() {
		return userToProjectEndpoint;
	}

	public void setUserToProjectEndpoint(UserToProjectEndpoint userToProjectEndpoint) {
		this.userToProjectEndpoint = userToProjectEndpoint;
	}

}