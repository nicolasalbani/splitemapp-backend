package com.splitemapp.commons.domain.dto.response;

import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.UserSessionDTO;
import com.splitemapp.commons.domain.dto.UserStatusDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;

public class LoginResponse extends ServiceResponse{

	private UserSessionDTO userSessionDTO;
	private UserDTO userDTO;
	private UserStatusDTO userStatusDTO;
	private UserContactDataDTO userContactDataDTO;
	private Boolean changePassword;

	public LoginResponse() {}

	public LoginResponse(Boolean success,UserSessionDTO userSessionDTO,UserContactDataDTO userContactDataDTO,Boolean changePassword) {
		super(success);
		this.userSessionDTO = userSessionDTO;
		this.userContactDataDTO = userContactDataDTO;
		this.changePassword	= changePassword;
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

	public UserContactDataDTO getUserContactDataDTO() {
		return userContactDataDTO;
	}

	public void setUserContactDataDTO(UserContactDataDTO userContactDataDTO) {
		this.userContactDataDTO = userContactDataDTO;
	}

	public Boolean getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Boolean changePassword) {
		this.changePassword = changePassword;
	}
}
