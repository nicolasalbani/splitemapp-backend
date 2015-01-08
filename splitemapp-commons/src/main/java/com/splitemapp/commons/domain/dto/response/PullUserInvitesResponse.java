package com.splitemapp.commons.domain.dto.response;

import java.util.Set;

import com.splitemapp.commons.domain.dto.UserInviteDTO;

public class PullUserInvitesResponse extends ServiceResponse{

	private Set<UserInviteDTO> userInviteDTOs;

	public PullUserInvitesResponse() {}

	public PullUserInvitesResponse(Boolean success, Set<UserInviteDTO> userInviteDTOs) {
		super(success);
		this.userInviteDTOs = userInviteDTOs;
	}

	public Set<UserInviteDTO> getUserInviteDTOs() {
		return userInviteDTOs;
	}

	public void setUserInviteDTOs(Set<UserInviteDTO> userInviteDTOs) {
		this.userInviteDTOs = userInviteDTOs;
	}

}
