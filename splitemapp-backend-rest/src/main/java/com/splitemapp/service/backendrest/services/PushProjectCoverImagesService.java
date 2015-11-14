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

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.Project;
import com.splitemapp.commons.domain.ProjectCoverImage;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.ProjectCoverImageDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.endpoint.ProjectCoverImageEndpoint;
import com.splitemapp.service.backendrest.endpoint.ProjectEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PUSH_PROJECT_COVER_IMAGES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushProjectCoverImagesService {

	UserSessionEndpoint userSessionEndpoint;
	ProjectEndpoint projectEndpoint;
	ProjectCoverImageEndpoint projectCoverImageEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}
	
	@POST
	public PushResponse<Long> printMessage(PushRequest<ProjectCoverImageDTO> request) throws ParseException {

		// We create a push project cover image response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(ProjectCoverImageDTO projectCoverImageDTO:request.getItemList()){
				// We create the project cover image object
				Project project = projectEndpoint.findById(projectCoverImageDTO.getProjectId());
				ProjectCoverImage projectCoverImage = new ProjectCoverImage(project, projectCoverImageDTO);
				
				if(Utils.isDateAfter(projectCoverImageDTO.getCreatedAt(),request.getLastPushSuccessAt())){
					// We persist the entry to the database
					projectCoverImage.setId(null);
					projectCoverImageEndpoint.persist(projectCoverImage);
					
					// We add the IdUpdate element to the response list
					response.getIdUpdateList().add(new IdUpdate<Long>(projectCoverImageDTO.getId(), projectCoverImage.getId()));
				} else {
					// We merge the entry to the database
					projectCoverImage.setUpdatedAt(new Date());
					projectCoverImageEndpoint.merge(projectCoverImage);
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
}