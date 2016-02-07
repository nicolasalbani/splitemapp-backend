package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.Action;
import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.Project;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.UserToProject;
import com.splitemapp.commons.domain.UserToProjectStatus;
import com.splitemapp.commons.domain.dto.UserToProjectDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.endpoint.ProjectEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserToProjectEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserToProjectStatusEndpoint;

@Service
@Path(ServiceConstants.PUSH_USER_TO_PROJECTS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushUserToProjectsService extends PushNotificationService{

	UserEndpoint userEndpoint;
	ProjectEndpoint projectEndpoint;
	UserToProjectStatusEndpoint userToProjectStatusEndpoint;
	UserToProjectEndpoint userToProjectEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PushResponse<Long> printMessage(PushRequest<UserToProjectDTO> request) throws ParseException {
		// We create a pull groups response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();
		response.setSuccess(false);

		// Creating the pushedAt date
		Date pushedAt = new Date();

		// Defining action and details to be notified
		String action = "";

		UserSession userSession = getUserSession(request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(UserToProjectDTO userToProjectDTO:request.getItemList()){
				// We create the user to project object
				User user = userEndpoint.findById(userToProjectDTO.getUserId());
				Project project = projectEndpoint.findById(userToProjectDTO.getProjectId());
				UserToProjectStatus userToProjectStatus = userToProjectStatusEndpoint.findById(userToProjectDTO.getUserToProjectStatusId());
				UserToProject userToProject = new UserToProject(user, project, userToProjectStatus, userToProjectDTO);

				// We update the pushedAt date
				userToProject.setPushedAt(pushedAt);

				if(Utils.isDateAfter(userToProjectDTO.getCreatedAt(),request.getLastPushSuccessAt())){
					// Setting the action
					action = Action.ADD_USER_TO_PROJECT;

					// We persist the entry to the database
					userToProject.setId(null);
					userToProjectEndpoint.persist(userToProject);

					// We add the IdUpdate element to the response list
					response.getIdUpdateList().add(new IdUpdate<Long>(userToProjectDTO.getId(), userToProject.getId()));
				} else {
					// Setting the action
					action = Action.UPDATE_USER_TO_PROJECT;

					// We merge the entry to the database
					userToProjectEndpoint.merge(userToProject);
				}

				// Sending GCM notification to all related clients
				sendGcmNotification(userSession, action, userToProject.getProject().getTitle(), userToProject.getProject().getId());
			}

			// We set the success flag and pushedAt
			response.setPushedAt(pushedAt);
			response.setSuccess(true);
		}

		return response;
	}

	// Getters and setters

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

	public UserToProjectStatusEndpoint getUserToProjectStatusEndpoint() {
		return userToProjectStatusEndpoint;
	}

	public void setUserToProjectStatusEndpoint(UserToProjectStatusEndpoint userToProjectStatusEndpoint) {
		this.userToProjectStatusEndpoint = userToProjectStatusEndpoint;
	}

	public UserToProjectEndpoint getUserToProjectEndpoint() {
		return userToProjectEndpoint;
	}

	public void setUserToProjectEndpoint(UserToProjectEndpoint userToProjectEndpoint) {
		this.userToProjectEndpoint = userToProjectEndpoint;
	}

}