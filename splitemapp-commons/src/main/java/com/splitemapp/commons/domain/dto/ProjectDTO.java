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
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date createdAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date updatedAt;
	
	public ProjectDTO(){}

	public ProjectDTO(Project project) {
		this.id = project.getId();
		this.projectTypeId = project.getProjectType().getId();
		this.projectStatusId = project.getProjectStatus().getId();
		this.title = project.getTitle();
		this.budget = project.getBudget();
		this.createdAt = project.getCreatedAt();
		this.updatedAt = project.getUpdatedAt();
	}

	public ProjectDTO(Short projectTypeId, Short projectStatusId,
			String title, String imgCover, BigDecimal budget, Date createdAt,
			Date updatedAt) {
		this.projectTypeId = projectTypeId;
		this.projectStatusId = projectStatusId;
		this.title = title;
		this.budget = budget;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	
}
