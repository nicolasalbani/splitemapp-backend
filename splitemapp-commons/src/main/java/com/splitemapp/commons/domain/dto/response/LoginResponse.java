package com.splitemapp.commons.domain.dto.response;

import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.UserSessionDTO;
import com.splitemapp.commons.domain.dto.UserStatusDTO;

public class LoginResponse {

	private Boolean success;
	private UserSessionDTO userSessionDTO;
	private UserDTO userDTO;
	private UserStatusDTO userStatusDTO;
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

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public UserStatusDTO getUserStatusDTO() {
		return userStatusDTO;
	}

	public void setUserStatusDTO(UserStatusDTO userStatusDTO) {
		this.userStatusDTO = userStatusDTO;
	}

	public Boolean getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Boolean changePassword) {
		this.changePassword = changePassword;
	}
}