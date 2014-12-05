package com.splitemapp.commons.domain.dto.response;

import java.util.Set;

import com.splitemapp.commons.domain.dto.GroupDTO;
import com.splitemapp.commons.domain.dto.ProjectDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserExpenseDTO;
import com.splitemapp.commons.domain.dto.UserInviteDTO;
import com.splitemapp.commons.domain.dto.UserToGroupDTO;
import com.splitemapp.commons.domain.dto.UserToProjectDTO;

public class PullAllSyncResponse {

	private Boolean success;
	private Set<UserContactDataDTO> userContactDataDTOs;
	private Set<GroupDTO> groupDTOs;
	private Set<ProjectDTO> projectDTOs;
	private Set<UserExpenseDTO> userExpenseDTOs;
	private Set<UserInviteDTO> userInviteDTOs;
	private Set<UserToGroupDTO> userToGroupDTOs;
	private Set<UserToProjectDTO> userToProjectDTOs;

	public PullAllSyncResponse() {}


	public PullAllSyncResponse(Boolean success,
			Set<UserContactDataDTO> userContactDataDTOs,
			Set<GroupDTO> groupDTOs, Set<ProjectDTO> projectDTOs,
			Set<UserExpenseDTO> userExpenseDTOs,
			Set<UserInviteDTO> userInviteDTOs,
			Set<UserToGroupDTO> userToGroupDTOs,
			Set<UserToProjectDTO> userToProjectDTOs) {
		super();
		this.success = success;
		this.userContactDataDTOs = userContactDataDTOs;
		this.groupDTOs = groupDTOs;
		this.projectDTOs = projectDTOs;
		this.userExpenseDTOs = userExpenseDTOs;
		this.userInviteDTOs = userInviteDTOs;
		this.userToGroupDTOs = userToGroupDTOs;
		this.userToProjectDTOs = userToProjectDTOs;
	}


	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Set<UserContactDataDTO> getUserContactDataDTOs() {
		return userContactDataDTOs;
	}

	public void setUserContactDataDTOs(Set<UserContactDataDTO> userContactDataDTOs) {
		this.userContactDataDTOs = userContactDataDTOs;
	}

	public Set<GroupDTO> getGroupDTOs() {
		return groupDTOs;
	}

	public void setGroupDTOs(Set<GroupDTO> groupDTOs) {
		this.groupDTOs = groupDTOs;
	}

	public Set<ProjectDTO> getProjectDTOs() {
		return projectDTOs;
	}

	public void setProjectDTOs(Set<ProjectDTO> projectDTOs) {
		this.projectDTOs = projectDTOs;
	}

	public Set<UserExpenseDTO> getUserExpenseDTOs() {
		return userExpenseDTOs;
	}

	public void setUserExpenseDTOs(Set<UserExpenseDTO> userExpenseDTOs) {
		this.userExpenseDTOs = userExpenseDTOs;
	}

	public Set<UserInviteDTO> getUserInviteDTOs() {
		return userInviteDTOs;
	}

	public void setUserInviteDTOs(Set<UserInviteDTO> userInviteDTOs) {
		this.userInviteDTOs = userInviteDTOs;
	}

	public Set<UserToGroupDTO> getUserToGroupDTOs() {
		return userToGroupDTOs;
	}

	public void setUserToGroupDTOs(Set<UserToGroupDTO> userToGroupDTOs) {
		this.userToGroupDTOs = userToGroupDTOs;
	}

	public Set<UserToProjectDTO> getUserToProjectDTOs() {
		return userToProjectDTOs;
	}

	public void setUserToProjectDTOs(Set<UserToProjectDTO> userToProjectDTOs) {
		this.userToProjectDTOs = userToProjectDTOs;
	}

}
