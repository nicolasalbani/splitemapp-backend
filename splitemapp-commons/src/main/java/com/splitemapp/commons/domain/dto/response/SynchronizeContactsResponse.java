package com.splitemapp.commons.domain.dto.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class SynchronizeContactsResponse extends ServiceResponse{

	private List<UserDTO> userDTOList;
	private List<UserContactDataDTO> userContactDataDTOList;
	private List<UserAvatarDTO> userAvatarDTOList;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date pulledAt;

	/**
	 * Required by FasterXML.
	 */
	public SynchronizeContactsResponse() {}

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

	public Date getPulledAt() {
		return pulledAt;
	}

	public void setPulledAt(Date pulledAt) {
		this.pulledAt = pulledAt;
	}
}
