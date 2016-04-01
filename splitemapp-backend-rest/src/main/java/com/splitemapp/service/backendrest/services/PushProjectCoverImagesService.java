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
import com.splitemapp.commons.domain.ProjectCoverImage;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.ProjectCoverImageDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.utils.TimeUtils;
import com.splitemapp.service.backendrest.endpoint.ProjectCoverImageEndpoint;
import com.splitemapp.service.backendrest.endpoint.ProjectEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;

@Service
@Path(ServiceConstants.PUSH_PROJECT_COVER_IMAGES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushProjectCoverImagesService extends PushNotificationService{

	ProjectEndpoint projectEndpoint;
	UserEndpoint userEndpoint;
	ProjectCoverImageEndpoint projectCoverImageEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}
	
	@POST
	public PushResponse<Long> printMessage(PushRequest<ProjectCoverImageDTO> request) throws ParseException {
		// We create a push project cover image response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();
		
		// Creating the pushedAt date
		Date pushedAt = TimeUtils.getUTCDate();
		
		// Defining action and details to be notified
		String action = "";

		UserSession userSession = getUserSession(request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(ProjectCoverImageDTO projectCoverImageDTO:request.getItemList()){
				// We create the project cover image object
				Project project = projectEndpoint.findById(projectCoverImageDTO.getProjectId());
				User updatedBy = userEndpoint.findById(projectCoverImageDTO.getUpdatedBy());
				User pushedBy = userEndpoint.findById(projectCoverImageDTO.getPushedBy());
				ProjectCoverImage projectCoverImage = new ProjectCoverImage(project, updatedBy, pushedBy, projectCoverImageDTO);
				
				// We update the pushedAt date
				projectCoverImage.setPushedAt(pushedAt);
				
				if(projectCoverImageDTO.getPushedAt() == null){
					// Setting the action
					action = Action.ADD_PROJECT_COVER_IMAGE;
					
					// We persist the entry to the database
					projectCoverImage.setId(null);
					projectCoverImageEndpoint.persist(projectCoverImage);
					
					// We add the IdUpdate element to the response list
					response.getIdUpdateList().add(new IdUpdate<Long>(projectCoverImageDTO.getId(), projectCoverImage.getId()));
				} else {
					// Setting the action
					action = Action.UPDATE_PROJECT_COVER_IMAGE;
					
					// We merge the entry to the database
					projectCoverImageEndpoint.merge(projectCoverImage);
				}

				// Sending GCM notification to all related clients
				sendGcmNotification(userSession, action, projectCoverImage.getProject().getTitle(), projectCoverImage.getProject().getId());
			}

			// We set the success flag and pushedAt date
			response.setPushedAt(pushedAt);
			response.setSuccess(true);
		}

		return response;
	}


	public ProjectEndpoint getProjectEndpoint() {
		return projectEndpoint;
	}

	public void setProjectEndpoint(ProjectEndpoint projectEndpoint) {
		this.projectEndpoint = projectEndpoint;
	}

	public ProjectCoverImageEndpoint getProjectCoverImageEndpoint() {
		return projectCoverImageEndpoint;
	}

	public void setProjectCoverImageEndpoint(ProjectCoverImageEndpoint projectCoverImageEndpoint) {
		this.projectCoverImageEndpoint = projectCoverImageEndpoint;
	}

	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}
}