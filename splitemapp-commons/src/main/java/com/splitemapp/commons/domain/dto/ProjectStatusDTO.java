package com.splitemapp.commons.domain.dto;

import com.splitemapp.commons.domain.ProjectStatus;

public class ProjectStatusDTO implements java.io.Serializable {

	private static final long serialVersionUID = -1826170015585538581L;
	
	private short id;
	private String cod;
	private String title;

	public ProjectStatusDTO(ProjectStatus projectStatus) {
		this.id = projectStatus.getId();
		this.cod = projectStatus.getCod();
		this.title = projectStatus.getTitle();
	}

	public ProjectStatusDTO(short id, String cod, String title) {
		this.id = id;
		this.cod = cod;
		this.title = title;
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
