package com.splitemapp.commons.domain.dto.response;

import com.splitemapp.commons.domain.dto.UserSessionDTO;

public class LoginResponse {

	private Boolean success;
	private UserSessionDTO userSessionDTO;
	private Boolean changePassword;
	
	public LoginResponse() {}
	
	public LoginResponse(Boolean success,UserSessionDTO userSessionDTO,Boolean changePassword) {
		this.setSuccess(success);
		this.userSessionDTO = userSessionDTO;
		this.changePassword	= changePassword;	
	}

	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public UserSessionDTO getUserSessionDTO() {
		return userSessionDTO;
	}

	public void setUserSessionDTO(UserSessionDTO userSessionDTO) {
		this.userSessionDTO = userSessionDTO;
	}

	public Boolean getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Boolean changePassword) {
		this.changePassword = changePassword;
	}
}
