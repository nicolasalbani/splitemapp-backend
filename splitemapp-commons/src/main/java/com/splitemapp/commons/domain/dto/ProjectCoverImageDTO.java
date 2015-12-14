package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.ProjectCoverImage;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class ProjectCoverImageDTO implements java.io.Serializable {

	private static final long serialVersionUID = 8018834364942997890L;

	private Long id;
	private Long projectId;
	private byte[] avatarData;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date createdAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date updatedAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date pushedAt;
	
	public ProjectCoverImageDTO(){}

	public ProjectCoverImageDTO(ProjectCoverImage projectCoverImage) {
		this.id = projectCoverImage.getId();
		this.projectId = projectCoverImage.getProject().getId();
		this.avatarData = projectCoverImage.getAvatarData();
		this.createdAt = projectCoverImage.getCreatedAt();
		this.updatedAt = projectCoverImage.getUpdatedAt();
		this.pushedAt = projectCoverImage.getPushedAt();
	}

	public ProjectCoverImageDTO(Long id, Long projectId, byte[] avatarData,
			Date createdAt, Date updatedAt, Date pushedAt) {
		this.id = id;
		this.projectId = projectId;
		this.avatarData = avatarData;
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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public byte[] getAvatarData() {
		return avatarData;
	}

	public void setAvatarData(byte[] avatarData) {
		this.avatarData = avatarData;
	}

}
