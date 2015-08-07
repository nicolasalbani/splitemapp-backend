package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
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
import com.splitemapp.commons.domain.ProjectCoverImage;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.ProjectCoverImageDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.response.PullResponse;
import com.splitemapp.service.backendrest.endpoint.ProjectCoverImageEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PULL_PROJECT_COVER_IMAGES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullProjectCoverImagesService {

	UserSessionEndpoint userSessionEndpoint;
	ProjectCoverImageEndpoint projectCoverImageEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}
	
	@POST
	public PullResponse<ProjectCoverImageDTO> printMessage(PullRequest request) throws ParseException {

		// We create a pull projects object setting success to false by default
		PullResponse<ProjectCoverImageDTO> response = new PullResponse<ProjectCoverImageDTO>();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			User user = userSession.getUser();
			
			// We set the project set
			Set<ProjectCoverImageDTO> projectCoverImageDTOs = new HashSet<ProjectCoverImageDTO>();
			List<ProjectCoverImage> findUpdatedAfter = projectCoverImageEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId());
			for(ProjectCoverImage projectCoverImage:findUpdatedAfter){
				projectCoverImageDTOs.add(new ProjectCoverImageDTO(projectCoverImage));
			}
			response.setItemSet(projectCoverImageDTOs);

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

	public ProjectCoverImageEndpoint getProjectCoverImageEndpoint() {
		return projectCoverImageEndpoint;
	}

	public void setProjectCoverImageEndpoint(
			ProjectCoverImageEndpoint projectCoverImageEndpoint) {
		this.projectCoverImageEndpoint = projectCoverImageEndpoint;
	}

}