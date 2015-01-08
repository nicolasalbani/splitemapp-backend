package com.splitemapp.commons.domain.dto.response;

import java.util.Set;

import com.splitemapp.commons.domain.dto.ProjectDTO;

public class PullProjectsResponse extends ServiceResponse{

	private Set<ProjectDTO> projectDTOs;

	public PullProjectsResponse() {}

	public PullProjectsResponse(Boolean success, Set<ProjectDTO> projectDTOs) {
		super(success);
		this.projectDTOs = projectDTOs;
	}

	public Set<ProjectDTO> getProjectDTOs() {
		return projectDTOs;
	}

	public void setProjectDTOs(Set<ProjectDTO> projectDTOs) {
		this.projectDTOs = projectDTOs;
	}

}
