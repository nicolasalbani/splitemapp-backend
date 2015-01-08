package com.splitemapp.commons.domain.dto.response;

import java.util.Set;

import com.splitemapp.commons.domain.dto.UserContactDataDTO;

public class PullUserContactDatasResponse extends ServiceResponse{

	private Set<UserContactDataDTO> userContactDataDTOs;

	public PullUserContactDatasResponse() {}

	public PullUserContactDatasResponse(Boolean success, Set<UserContactDataDTO> userContactDataDTOs) {
		super(success);
		this.userContactDataDTOs = userContactDataDTOs;
	}

	public Set<UserContactDataDTO> getUserContactDataDTOs() {
		return userContactDataDTOs;
	}

	public void setUserContactDataDTOs(Set<UserContactDataDTO> userContactDataDTOs) {
		this.userContactDataDTOs = userContactDataDTOs;
	}

}
