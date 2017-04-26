package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class UserContactDataDTO implements java.io.Serializable {

	private static final long serialVersionUID = 8927823575372766924L;

	private Long id;
	private Long userId;
	private String contactData;
	private boolean verified;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date verifiedAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date createdAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date updatedAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date pushedAt;
	
	private Long updatedBy;
	private Long pushedBy;
	
	public UserContactDataDTO(){}

	public UserContactDataDTO(UserContactData userContactData) {
		this.id = userContactData.getId();
		this.userId = userContactData.getUser().getId();
		this.contactData = userContactData.getContactData();
		this.verified = userContactData.isVerified();
		this.verifiedAt = userContactData.getVerifiedAt();
		this.createdAt = userContactData.getCreatedAt();
		this.updatedAt = userContactData.getUpdatedAt();
		this.updatedBy = userContactData.getUpdatedBy().getId();
		this.pushedAt = userContactData.getPushedAt();
		this.pushedBy = userContactData.getPushedBy().getId();
	}

	public UserContactDataDTO(Long userId, String contactData, boolean verified,
			Date verifiedAt, Date createdAt, Date updatedAt, Date pushedAt) {
		this.userId = userId;
		this.contactData = contactData;
		this.verified = verified;
		this.verifiedAt = verifiedAt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.pushedAt = pushedAt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContactData() {
		return this.contactData;
	}

	public void setContactData(String contactData) {
		this.contactData = contactData;
	}

	public boolean isVerified() {
		return this.verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Date getVerifiedAt() {
		return this.verifiedAt;
	}

	public void setVerifiedAt(Date verifiedAt) {
		this.verifiedAt = verifiedAt;
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

	public Date getPushedAt() {
		return pushedAt;
	}

	public void setPushedAt(Date pushedAt) {
		this.pushedAt = pushedAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getPushedBy() {
		return pushedBy;
	}

	public void setPushedBy(Long pushedBy) {
		this.pushedBy = pushedBy;
	}
}
