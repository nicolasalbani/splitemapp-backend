package com.splitemapp.commons.domain.dto.response;

import java.util.Set;

import com.splitemapp.commons.domain.dto.GroupDTO;

public class PullGroupsResponse extends ServiceResponse{

	private Set<GroupDTO> groupDTOs;

	public PullGroupsResponse() {}

	public PullGroupsResponse(Boolean success, Set<GroupDTO> groupDTOs) {
		super(success);
		this.setGroupDTOs(groupDTOs);
	}

	public Set<GroupDTO> getGroupDTOs() {
		return groupDTOs;
	}

	public void setGroupDTOs(Set<GroupDTO> groupDTOs) {
		this.groupDTOs = groupDTOs;
	}

}
