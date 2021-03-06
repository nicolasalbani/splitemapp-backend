package com.splitemapp.commons.domain.dto;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.UserToProject;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class UserToProjectDTO implements java.io.Serializable {

	private static final long serialVersionUID = 7814066964126398718L;

	private Long id;
	private Long userId;
	private Long projectId;
	private Short userToProjectStatusId;
	private Float expensesShare;
	private Long updatedBy;
	private Long pushedBy;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date createdAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date updatedAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date pushedAt;
	
	public UserToProjectDTO(){}

	public UserToProjectDTO(UserToProject userToProject) {
		this.id = userToProject.getId();
		this.userId = userToProject.getUser().getId();
		this.projectId = userToProject.getProject().getId();
		this.userToProjectStatusId = userToProject.getUserToProjectStatus().getId();
		this.expensesShare = userToProject.getExpensesShare();
		this.createdAt = userToProject.getCreatedAt();
		this.updatedAt = userToProject.getUpdatedAt();
		this.updatedBy = userToProject.getUpdatedBy().getId();
		this.pushedAt = userToProject.getPushedAt();
		if(userToProject.getPushedBy() != null){
			this.pushedBy = userToProject.getPushedBy().getId();
		}
	}

	public UserToProjectDTO(Long userId, Long projectId,
			Short userToProjectStatusId, Float expensesShare) {
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

	public Float getExpensesShare() {
		return this.expensesShare;
	}

	public void setExpensesShare(Float expensesShare) {
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

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
