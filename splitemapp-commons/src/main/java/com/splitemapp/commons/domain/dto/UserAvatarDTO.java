package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.UserAvatar;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class UserAvatarDTO implements java.io.Serializable {

	private static final long serialVersionUID = 8018834364942997890L;

	private Long id;
	private Long userId;
	private byte[] avatarData;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date createdAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date updatedAt;
	
	public UserAvatarDTO(){}

	public UserAvatarDTO(UserAvatar userAvatar) {
		this.id = userAvatar.getId();
		this.userId = userAvatar.getUser().getId();
		this.avatarData = userAvatar.getAvatarData();
		this.createdAt = userAvatar.getCreatedAt();
		this.updatedAt = userAvatar.getUpdatedAt();
	}

	public UserAvatarDTO(Long id, Long userId, byte[] avatarData,
			Date createdAt, Date updatedAt) {
		this.id = id;
		this.userId = userId;
		this.avatarData = avatarData;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public byte[] getAvatarData() {
		return avatarData;
	}

	public void setAvatarData(byte[] avatarData) {
		this.avatarData = avatarData;
	}

}