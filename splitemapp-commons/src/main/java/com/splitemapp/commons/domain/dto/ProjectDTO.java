package com.splitemapp.commons.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.splitemapp.commons.domain.Project;

public class ProjectDTO implements java.io.Serializable {

	private static final long serialVersionUID = -1186403556721128678L;
	
	private Long id;
	private ProjectTypeDTO projectTypeDTO;
	private ProjectStatusDTO projectStatusDTO;
	private String title;
	private String imgCover;
	private BigDecimal budget;
	private Date createdAt;
	private Date updatedAt;
	
	public ProjectDTO(){}

	public ProjectDTO(Project project) {
		this.projectTypeDTO = new ProjectTypeDTO(project.getProjectType());
		this.projectStatusDTO = new ProjectStatusDTO(project.getProjectStatus());
		this.title = project.getTitle();
		this.imgCover = project.getImgCover();
		this.budget = project.getBudget();
		this.createdAt = project.getCreatedAt();
		this.updatedAt = project.getUpdatedAt();
	}

	public ProjectDTO(ProjectTypeDTO projectType, ProjectStatusDTO projectStatus,
			String title, String imgCover, BigDecimal budget, Date createdAt,
			Date updatedAt) {
		this.projectTypeDTO = projectType;
		this.projectStatusDTO = projectStatus;
		this.title = title;
		this.imgCover = imgCover;
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

	public ProjectTypeDTO getProjectTypeDTO() {
		return this.projectTypeDTO;
	}

	public void setProjectTypeDTO(ProjectTypeDTO projectTypeDTO) {
		this.projectTypeDTO = projectTypeDTO;
	}

	public ProjectStatusDTO getProjectStatusDTO() {
		return this.projectStatusDTO;
	}

	public void setProjectStatusDTO(ProjectStatusDTO projectStatusDTO) {
		this.projectStatusDTO = projectStatusDTO;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgCover() {
		return this.imgCover;
	}

	public void setImgCover(String imgCover) {
		this.imgCover = imgCover;
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
