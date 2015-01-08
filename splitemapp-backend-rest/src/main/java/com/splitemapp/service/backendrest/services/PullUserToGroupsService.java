package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.UserToGroup;
import com.splitemapp.commons.domain.dto.UserToGroupDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.response.PullUserToGroupsResponse;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserToGroupEndpoint;

@Service
@Path(ServiceConstants.PULL_USER_TO_GROUPS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullUserToGroupsService {

	UserSessionEndpoint userSessionEndpoint;
	UserToGroupEndpoint userToGroupEndpoint;

	@POST
	public PullUserToGroupsResponse printMessage(PullRequest request) throws ParseException {

		// We create a pull all sync response object setting success to false by default
		PullUserToGroupsResponse response = new PullUserToGroupsResponse();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			User user = userSession.getUser();
			
			// We set the user to group set
			Set<UserToGroupDTO> userToGroupDTOs = new HashSet<UserToGroupDTO>();
			for(UserToGroup userToGroup:userToGroupEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				userToGroupDTOs.add(new UserToGroupDTO(userToGroup));
			}
			response.setUserToGroupDTOs(userToGroupDTOs);

			// We set the success flag
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

	public UserToGroupEndpoint getUserToGroupEndpoint() {
		return userToGroupEndpoint;
	}

}