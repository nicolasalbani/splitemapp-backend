package com.splitemapp.commons.domain.dto.response;

import java.util.List;

import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;

public class SynchronizeContactsResponse extends ServiceResponse{

	private List<UserDTO> userDTOList;
	private List<UserContactDataDTO> userContactDataDTOList;
	private List<UserAvatarDTO> userAvatarDTOList;

	/**
	 * Required by FasterXML.
	 */
	public SynchronizeContactsResponse() {}

	public SynchronizeContactsResponse(Boolean success, List<UserDTO> userDTOList, List<UserContactDataDTO> userContactDataDTOList, List<UserAvatarDTO> userAvatarDTOList) {
		super(success);
		this.userDTOList = userDTOList;
		this.userContactDataDTOList = userContactDataDTOList;
		this.userAvatarDTOList = userAvatarDTOList;
	}

	public List<UserDTO> getUserDTOList() {
		return userDTOList;
	}

	public void setUserDTOList(List<UserDTO> userDTOList) {
		this.userDTOList = userDTOList;
	}

	public List<UserContactDataDTO> getUserContactDataDTOList() {
		return userContactDataDTOList;
	}

	public void setUserContactDataDTOList(List<UserContactDataDTO> userContactDataDTOList) {
		this.userContactDataDTOList = userContactDataDTOList;
	}

	public List<UserAvatarDTO> getUserAvatarDTOList() {
		return userAvatarDTOList;
	}

	public void setUserAvatarDTOList(List<UserAvatarDTO> userAvatarDTOList) {
		this.userAvatarDTOList = userAvatarDTOList;
	}
}
