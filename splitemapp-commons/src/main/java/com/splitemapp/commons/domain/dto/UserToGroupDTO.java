package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.splitemapp.commons.domain.UserToGroup;

public class UserToGroupDTO implements java.io.Serializable {

	private static final long serialVersionUID = 9173640802573250421L;

	private Long id;
	private Long userId;
	private Long groupId;
	private Short userToGroupStatusId;
	private Date createdAt;
	private Date updatedAt;
	private boolean isAdmin;
	
	public UserToGroupDTO(){}

	public UserToGroupDTO(UserToGroup userToGroup) {
		this.userId = userToGroup.getUser().getId();
		this.groupId = userToGroup.getGroup().getId();
		this.userToGroupStatusId = userToGroup.getUserToGroupStatus().getId();
		this.isAdmin = userToGroup.isIsAdmin();

	}

	public UserToGroupDTO(Long userId, Long groupId,
			Short userToGroupStatusId, boolean isAdmin) {
		this.userId = userId;
		this.groupId = groupId;
		this.userToGroupStatusId = userToGroupStatusId;
		this.isAdmin = isAdmin;
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

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Short getUserToGroupStatusId() {
		return this.userToGroupStatusId;
	}

	public void setUserToGroupStatusId(Short userToGroupStatusId) {
		this.userToGroupStatusId = userToGroupStatusId;
	}

	public boolean isIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
