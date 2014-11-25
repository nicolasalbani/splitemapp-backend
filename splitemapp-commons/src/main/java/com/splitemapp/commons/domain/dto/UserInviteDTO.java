package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.splitemapp.commons.domain.UserInvite;

public class UserInviteDTO implements java.io.Serializable {

	private static final long serialVersionUID = -2499430771175807337L;

	private Long id;
	private Long userId;
	private Long projectId;
	private Short inviteStatusId;
	private String email;
	private Date createdAt;
	private Date updatedAt;
	
	public UserInviteDTO(){}

	public UserInviteDTO(UserInvite userInvite) {
		this.userId = userInvite.getUser().getId();
		this.projectId = userInvite.getProject().getId();
		this.inviteStatusId = userInvite.getInviteStatus().getId();
		this.createdAt = userInvite.getCreatedAt();
		this.updatedAt = userInvite.getUpdatedAt();
	}

	public UserInviteDTO(Long userId, Long projectId, Short inviteStatusId,
			String email, Date createdAt, Date updatedAt) {
		this.userId = userId;
		this.projectId = projectId;
		this.inviteStatusId = inviteStatusId;
		this.email = email;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Short getInviteStatusId() {
		return this.inviteStatusId;
	}

	public void setInviteStatusId(Short inviteStatusId) {
		this.inviteStatusId = inviteStatusId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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
