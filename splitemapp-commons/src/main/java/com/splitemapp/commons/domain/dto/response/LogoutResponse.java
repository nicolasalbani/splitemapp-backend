package com.splitemapp.commons.domain.dto.response;

import com.splitemapp.commons.domain.dto.UserSessionDTO;

public class LogoutResponse extends ServiceResponse{

	private UserSessionDTO userSessionDTO;

	public LogoutResponse() {}

	public LogoutResponse(Boolean success,UserSessionDTO userSessionDTO) {
		super(success);
		this.userSessionDTO = userSessionDTO;
	}

	public UserSessionDTO getUserSessionDTO() {
		return userSessionDTO;
	}

	public void setUserSessionDTO(UserSessionDTO userSessionDTO) {
		this.userSessionDTO = userSessionDTO;
	}
}
