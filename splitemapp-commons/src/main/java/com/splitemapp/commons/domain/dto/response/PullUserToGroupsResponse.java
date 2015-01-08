package com.splitemapp.commons.domain.dto.response;

import java.util.Set;

import com.splitemapp.commons.domain.dto.UserToGroupDTO;

public class PullUserToGroupsResponse extends ServiceResponse{

	private Set<UserToGroupDTO> userToGroupDTOs;

	public PullUserToGroupsResponse() {}

	public PullUserToGroupsResponse(Boolean success, Set<UserToGroupDTO> userToGroupDTOs) {
		super(success);
		this.userToGroupDTOs = userToGroupDTOs;
	}

	public Set<UserToGroupDTO> getUserToGroupDTOs() {
		return userToGroupDTOs;
	}

	public void setUserToGroupDTOs(Set<UserToGroupDTO> userToGroupDTOs) {
		this.userToGroupDTOs = userToGroupDTOs;
	}
}
