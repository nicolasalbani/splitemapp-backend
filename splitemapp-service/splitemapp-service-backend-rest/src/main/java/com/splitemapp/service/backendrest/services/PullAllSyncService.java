package com.splitemapp.service.backendrest.services;

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
import com.splitemapp.commons.domain.Project;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.commons.domain.UserInvite;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.UserToGroup;
import com.splitemapp.commons.domain.UserToProject;
import com.splitemapp.commons.domain.dto.GroupDTO;
import com.splitemapp.commons.domain.dto.ProjectDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserExpenseDTO;
import com.splitemapp.commons.domain.dto.UserInviteDTO;
import com.splitemapp.commons.domain.dto.UserToGroupDTO;
import com.splitemapp.commons.domain.dto.UserToProjectDTO;
import com.splitemapp.service.backendrest.endpoint.GroupEndpoint;
import com.splitemapp.service.backendrest.endpoint.ProjectEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserContactDataEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserExpenseEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserInviteEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserToGroupEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserToProjectEndpoint;
import com.splitemapp.service.domainmodel.dto.PullAllSyncRequest;
import com.splitemapp.service.domainmodel.dto.PullAllSyncResponse;

@Service
@Path(ServiceConstants.PULL_ALL_SYNC_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullAllSyncService {

	UserSessionEndpoint userSessionEndpoint;
	UserContactDataEndpoint userContactDataEndpoint;
	GroupEndpoint groupEndpoint;
	ProjectEndpoint projectEndpoint;
	UserExpenseEndpoint userExpenseEndpoint;
	UserInviteEndpoint userInviteEndpoint;
	UserToGroupEndpoint userToGroupEndpoint;
	UserToProjectEndpoint userToProjectEndpoint;

	@POST
	public PullAllSyncResponse printMessage(PullAllSyncRequest request) {

		// We create a pull all sync response object setting success to false by default
		PullAllSyncResponse response = new PullAllSyncResponse();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			User user = userSession.getUser();
			
			// We set the user contact data set
			Set<UserContactDataDTO> userContactDataDTOs = new HashSet<UserContactDataDTO>();
			for(UserContactData userContactData:userContactDataEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				userContactDataDTOs.add(new UserContactDataDTO(userContactData));
			}
			response.setUserContactDataDTOs(userContactDataDTOs);

			// We set the group set
			Set<GroupDTO> groupDTOs = new HashSet<GroupDTO>();
			for(Group group:groupEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				groupDTOs.add(new GroupDTO(group));
			}
			response.setGroupDTOs(groupDTOs);

			// We set the project set
			Set<ProjectDTO> projectDTOs = new HashSet<ProjectDTO>();
			for(Project project:projectEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				projectDTOs.add(new ProjectDTO(project));
			}
			response.setProjectDTOs(projectDTOs);

			// We set the user expense set
			Set<UserExpenseDTO> userExpenseDTOs = new HashSet<UserExpenseDTO>();
			for(UserExpense userExpense:userExpenseEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				userExpenseDTOs.add(new UserExpenseDTO(userExpense));
			}
			response.setUserExpenseDTOs(userExpenseDTOs);

			// We set the user invite set
			Set<UserInviteDTO> userInviteDTOs = new HashSet<UserInviteDTO>();
			for(UserInvite userInvite:userInviteEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				userInviteDTOs.add(new UserInviteDTO(userInvite));
			}
			response.setUserInviteDTOs(userInviteDTOs);

			// We set the user to group set
			Set<UserToGroupDTO> userToGroupDTOs = new HashSet<UserToGroupDTO>();
			for(UserToGroup userToGroup:userToGroupEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				userToGroupDTOs.add(new UserToGroupDTO(userToGroup));
			}
			response.setUserToGroupDTOs(userToGroupDTOs);

			// We set the user to project set
			Set<UserToProjectDTO> userToProjectDTOs = new HashSet<UserToProjectDTO>();
			for(UserToProject userToProject:userToProjectEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				userToProjectDTOs.add(new UserToProjectDTO(userToProject));
			}
			response.setUserToProjectDTOs(userToProjectDTOs);
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

	public UserContactDataEndpoint getUserContactDataEndpoint() {
		return userContactDataEndpoint;
	}

	public void setUserContactDataEndpoint(
			UserContactDataEndpoint userContactDataEndpoint) {
		this.userContactDataEndpoint = userContactDataEndpoint;
	}

	public GroupEndpoint getGroupEndpoint() {
		return groupEndpoint;
	}

	public void setGroupEndpoint(GroupEndpoint groupEndpoint) {
		this.groupEndpoint = groupEndpoint;
	}

	public ProjectEndpoint getProjectEndpoint() {
		return projectEndpoint;
	}

	public void setProjectEndpoint(ProjectEndpoint projectEndpoint) {
		this.projectEndpoint = projectEndpoint;
	}

	public UserExpenseEndpoint getUserExpenseEndpoint() {
		return userExpenseEndpoint;
	}

	public void setUserExpenseEndpoint(UserExpenseEndpoint userExpenseEndpoint) {
		this.userExpenseEndpoint = userExpenseEndpoint;
	}

	public UserInviteEndpoint getUserInviteEndpoint() {
		return userInviteEndpoint;
	}

	public void setUserInviteEndpoint(UserInviteEndpoint userInviteEndpoint) {
		this.userInviteEndpoint = userInviteEndpoint;
	}

	public UserToGroupEndpoint getUserToGroupEndpoint() {
		return userToGroupEndpoint;
	}

	public void setUserToGroupEndpoint(UserToGroupEndpoint userToGroupEndpoint) {
		this.userToGroupEndpoint = userToGroupEndpoint;
	}

	public UserToProjectEndpoint getUserToProjectEndpoint() {
		return userToProjectEndpoint;
	}

	public void setUserToProjectEndpoint(UserToProjectEndpoint userToProjectEndpoint) {
		this.userToProjectEndpoint = userToProjectEndpoint;
	}

}