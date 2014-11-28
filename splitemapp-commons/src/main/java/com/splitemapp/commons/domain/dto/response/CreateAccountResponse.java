package com.splitemapp.commons.domain.dto.response;

import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.UserStatusDTO;

public class CreateAccountResponse {

	private Boolean success;
	private UserDTO userDTO;
	private UserStatusDTO userStatusDTO;
	private UserContactDataDTO userContactDataDTO;

	public CreateAccountResponse() {}

	public CreateAccountResponse(Boolean success, UserDTO userDTO, UserStatusDTO userStatusDTO, UserContactDataDTO userContactDataDTO) {
		this.setSuccess(success);
		this.setUserDTO(userDTO);
		this.setUserStatusDTO(userStatusDTO);
		this.setUserContactDataDTO(userContactDataDTO);
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public UserContactDataDTO getUserContactDataDTO() {
		return userContactDataDTO;
	}

	public void setUserContactDataDTO(UserContactDataDTO userContactDataDTO) {
		this.userContactDataDTO = userContactDataDTO;
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
}
