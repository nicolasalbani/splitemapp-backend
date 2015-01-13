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
import com.splitemapp.commons.domain.Group;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.GroupDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.response.PullResponse;
import com.splitemapp.service.backendrest.endpoint.GroupEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PULL_GROUPS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullGroupsService {

	UserSessionEndpoint userSessionEndpoint;
	GroupEndpoint groupEndpoint;

	@POST
	public PullResponse<GroupDTO> printMessage(PullRequest request) throws ParseException {

		// We create a pull groups response object setting success to false by default
		PullResponse<GroupDTO> response = new PullResponse<GroupDTO>();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			User user = userSession.getUser();

			// We set the group set
			Set<GroupDTO> groupDTOs = new HashSet<GroupDTO>();
			for(Group group:groupEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				groupDTOs.add(new GroupDTO(group));
			}
			response.setItemSet(groupDTOs);

			// We set the success flag
			response.setSuccess(true);
		}

		return response;
	}


	// Getters and setters

	public GroupEndpoint getGroupEndpoint() {
		return groupEndpoint;
	}

	public void setGroupEndpoint(GroupEndpoint groupEndpoint) {
		this.groupEndpoint = groupEndpoint;
	}

}