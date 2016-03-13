package com.splitemapp.commons.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.Project;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class ProjectDTO implements java.io.Serializable {

	private static final long serialVersionUID = -1186403556721128678L;
	
	private Long id;
	private Short projectTypeId;
	private Short projectStatusId;
	private String title;
	private BigDecimal budget;
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
	
	public ProjectDTO(){}

	public ProjectDTO(Project project) {
		this.id = project.getId();
		this.projectTypeId = project.getProjectType().getId();
		this.projectStatusId = project.getProjectStatus().getId();
		this.title = project.getTitle();
		this.budget = project.getBudget();
		this.createdAt = project.getCreatedAt();
		this.updatedAt = project.getUpdatedAt();
		this.updatedBy = project.getUpdatedBy().getId();
		this.pushedAt = project.getPushedAt();
		if(project.getPushedBy() != null){
			this.pushedBy = project.getPushedBy().getId();
		}
	}

	public ProjectDTO(Short projectTypeId, Short projectStatusId,
			String title, String imgCover, BigDecimal budget, Date createdAt,
			Date updatedAt, Date pushedAt) {
		this.projectTypeId = projectTypeId;
		this.projectStatusId = projectStatusId;
		this.title = title;
		this.budget = budget;
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

	public Short getProjectTypeId() {
		return this.projectTypeId;
	}

	public void setProjectTypeId(Short projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public Short getProjectStatusId() {
		return this.projectStatusId;
	}

	public void setProjectStatusId(Short projectStatusId) {
		this.projectStatusId = projectStatusId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getBudget() {
		return this.budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
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
