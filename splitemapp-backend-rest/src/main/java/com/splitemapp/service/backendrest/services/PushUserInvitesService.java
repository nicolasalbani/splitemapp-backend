package com.splitemapp.service.backendrest.services;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.InviteStatus;
import com.splitemapp.commons.domain.Project;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserInvite;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserInviteDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.endpoint.InviteStatusEndpoint;
import com.splitemapp.service.backendrest.endpoint.ProjectEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserInviteEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PUSH_USER_INVITES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushUserInvitesService {

	UserSessionEndpoint userSessionEndpoint;
	UserEndpoint userEndpoint;
	ProjectEndpoint projectEndpoint;
	InviteStatusEndpoint inviteStatusEndpoint;
	UserInviteEndpoint userInviteEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}
	
	@POST
	public PushResponse<Long> printMessage(PushRequest<UserInviteDTO> request) throws ParseException {

		// We create a pull groups response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(UserInviteDTO userInviteDTO:request.getItemList()){
				// We create the user invite object
				User user = userEndpoint.findById(userInviteDTO.getUserId());
				Project project = projectEndpoint.findById(userInviteDTO.getProjectId());
				InviteStatus inviteStatus = inviteStatusEndpoint.findById(userInviteDTO.getInviteStatusId());
				UserInvite userInvite = new UserInvite(user, project, inviteStatus, userInviteDTO);
				
				if(Utils.isDateAfter(userInviteDTO.getCreatedAt(),request.getLastPushSuccessAt())){
					// We persist the entry to the database
					userInvite.setId(null);
					userInviteEndpoint.persist(userInvite);
					
					// We add the IdUpdate element to the response list
					response.getIdUpdateList().add(new IdUpdate<Long>(userInviteDTO.getId(), userInvite.getId()));
				} else {
					// We merge the entry to the database
					userInviteEndpoint.merge(userInvite);
				}
			}

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

	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

	public ProjectEndpoint getProjectEndpoint() {
		return projectEndpoint;
	}

	public void setProjectEndpoint(ProjectEndpoint projectEndpoint) {
		this.projectEndpoint = projectEndpoint;
	}

	public InviteStatusEndpoint getInviteStatusEndpoint() {
		return inviteStatusEndpoint;
	}

	public void setInviteStatusEndpoint(InviteStatusEndpoint inviteStatusEndpoint) {
		this.inviteStatusEndpoint = inviteStatusEndpoint;
	}

	public UserInviteEndpoint getUserInviteEndpoint() {
		return userInviteEndpoint;
	}

	public void setUserInviteEndpoint(UserInviteEndpoint userInviteEndpoint) {
		this.userInviteEndpoint = userInviteEndpoint;
	}

}