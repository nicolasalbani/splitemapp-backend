package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.splitemapp.commons.domain.UserContactData;

public class UserContactDataDTO implements java.io.Serializable {

	private static final long serialVersionUID = 8927823575372766924L;

	private Long id;
	private UserDTO userDTO;
	private String contactData;
	private boolean verified;
	private Date verifiedAt;
	private Date createdAt;
	private Date updatedAt;
	
	public UserContactDataDTO(){}

	public UserContactDataDTO(UserContactData userContactData) {
		this.userDTO = new UserDTO(userContactData.getUser());
		this.contactData = userContactData.getContactData();
		this.verified = userContactData.isVerified();
		this.verifiedAt = userContactData.getVerifiedAt();
		this.createdAt = userContactData.getCreatedAt();
		this.updatedAt = userContactData.getUpdatedAt();
	}

	public UserContactDataDTO(UserDTO userDTO, String contactData, boolean verified,
			Date verifiedAt, Date createdAt, Date updatedAt) {
		this.userDTO = userDTO;
		this.contactData = contactData;
		this.verified = verified;
		this.verifiedAt = verifiedAt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return this.userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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

}
