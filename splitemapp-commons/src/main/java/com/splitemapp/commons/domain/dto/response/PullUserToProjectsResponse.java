package com.splitemapp.commons.domain.dto.response;

import java.util.Set;

import com.splitemapp.commons.domain.dto.UserToProjectDTO;

public class PullUserToProjectsResponse extends ServiceResponse{

	private Set<UserToProjectDTO> userToProjectDTOs;

	public PullUserToProjectsResponse() {}

	public PullUserToProjectsResponse(Boolean success, Set<UserToProjectDTO> userToProjectDTOs) {
		super(success);
		this.userToProjectDTOs = userToProjectDTOs;
	}

	public Set<UserToProjectDTO> getUserToProjectDTOs() {
		return userToProjectDTOs;
	}

	public void setUserToProjectDTOs(Set<UserToProjectDTO> userToProjectDTOs) {
		this.userToProjectDTOs = userToProjectDTOs;
	}
}
