package com.splitemapp.commons.domain.dto;


import java.math.BigDecimal;
import java.util.Date;

import com.splitemapp.commons.domain.UserToProject;

public class UserToProjectDTO implements java.io.Serializable {

	private static final long serialVersionUID = 7814066964126398718L;

	private Long id;
	private Long userId;
	private Long projectId;
	private Short userToProjectStatusId;
	private Date createdAt;
	private Date updatedAt;
	private BigDecimal expensesShare;
	
	public UserToProjectDTO(){}

	public UserToProjectDTO(UserToProject userToProject) {
		this.userId = userToProject.getUser().getId();
		this.projectId = userToProject.getProject().getId();
		this.userToProjectStatusId = userToProject.getUserToProjectStatus().getId();
		this.expensesShare = userToProject.getExpensesShare();
	}

	public UserToProjectDTO(Long userId, Long projectId,
			Short userToProjectStatusId, BigDecimal expensesShare) {
		this.userId = userId;
		this.projectId = projectId;
		this.userToProjectStatusId = userToProjectStatusId;
		this.expensesShare = expensesShare;
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

	public void setProjectDTO(Long projectId) {
		this.projectId = projectId;
	}

	public Short getUserToProjectStatusId() {
		return this.userToProjectStatusId;
	}

	public void setUserToProjectStatusId(Short userToProjectStatusId) {
		this.userToProjectStatusId = userToProjectStatusId;
	}

	public BigDecimal getExpensesShare() {
		return this.expensesShare;
	}

	public void setExpensesShare(BigDecimal expensesShare) {
		this.expensesShare = expensesShare;
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
