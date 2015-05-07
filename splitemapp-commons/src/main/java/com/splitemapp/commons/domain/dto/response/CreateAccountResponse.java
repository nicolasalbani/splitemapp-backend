package com.splitemapp.commons.domain.dto.response;

import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.UserStatusDTO;

public class CreateAccountResponse extends ServiceResponse{

	private UserDTO userDTO;
	private UserAvatarDTO userAvatarDTO;
	private UserStatusDTO userStatusDTO;
	private UserContactDataDTO userContactDataDTO;

	public CreateAccountResponse() {}

	public CreateAccountResponse(Boolean success, UserDTO userDTO, UserAvatarDTO userAvatarDTO, UserStatusDTO userStatusDTO, UserContactDataDTO userContactDataDTO) {
		super(success);
		this.setUserDTO(userDTO);
		this.setUserAvatarDTO(userAvatarDTO);
		this.setUserStatusDTO(userStatusDTO);
		this.setUserContactDataDTO(userContactDataDTO);
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

	public UserAvatarDTO getUserAvatarDTO() {
		return userAvatarDTO;
	}

	public void setUserAvatarDTO(UserAvatarDTO userAvatarDTO) {
		this.userAvatarDTO = userAvatarDTO;
	}
}
