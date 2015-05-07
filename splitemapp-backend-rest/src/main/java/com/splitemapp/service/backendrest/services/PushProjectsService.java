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
import com.splitemapp.commons.constants.TableFieldCod;
import com.splitemapp.commons.domain.Project;
import com.splitemapp.commons.domain.ProjectStatus;
import com.splitemapp.commons.domain.ProjectType;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.ProjectDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.endpoint.ProjectEndpoint;
import com.splitemapp.service.backendrest.endpoint.ProjectStatusEndpoint;
import com.splitemapp.service.backendrest.endpoint.ProjectTypeEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PUSH_PROJECTS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushProjectsService {

	UserSessionEndpoint userSessionEndpoint;
	ProjectStatusEndpoint projectStatusEndpoint;
	ProjectTypeEndpoint projectTypeEndpoint;
	ProjectEndpoint projectEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}
	
	@POST
	public PushResponse<Long> printMessage(PushRequest<ProjectDTO> request) throws ParseException {

		// We create a pull groups response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(ProjectDTO projectDTO:request.getItemList()){
				// We create the project object with an active project status and the incoming type
				ProjectStatus projectStatus = projectStatusEndpoint.findByField(TableField.ALTER_TABLE_COD, TableFieldCod.GROUP_STATUS_ACTIVE);
				ProjectType projectType = projectTypeEndpoint.findById(projectDTO.getProjectTypeId());
				Project project = new Project(projectType, projectStatus, projectDTO);
				
				if(Utils.isDateAfter(projectDTO.getCreatedAt(),request.getLastPushSuccessAt())){
					// We persist the entry to the database
					project.setId(null);
					projectEndpoint.persist(project);
					
					// We add the IdUpdate element to the response list
					response.getIdUpdateList().add(new IdUpdate<Long>(projectDTO.getId(), project.getId()));
				} else {
					// We merge the entry to the database
					projectEndpoint.merge(project);
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

	public ProjectStatusEndpoint getProjectStatusEndpoint() {
		return projectStatusEndpoint;
	}

	public void setProjectStatusEndpoint(ProjectStatusEndpoint projectStatusEndpoint) {
		this.projectStatusEndpoint = projectStatusEndpoint;
	}

	public ProjectTypeEndpoint getProjectTypeEndpoint() {
		return projectTypeEndpoint;
	}

	public void setProjectTypeEndpoint(ProjectTypeEndpoint projectTypeEndpoint) {
		this.projectTypeEndpoint = projectTypeEndpoint;
	}

	public ProjectEndpoint getProjectEndpoint() {
		return projectEndpoint;
	}

	public void setProjectEndpoint(ProjectEndpoint projectEndpoint) {
		this.projectEndpoint = projectEndpoint;
	}
}