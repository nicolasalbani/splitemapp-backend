package com.splitemapp.commons.domain.dto;

import com.splitemapp.commons.domain.ProjectType;

public class ProjectTypeDTO implements java.io.Serializable {

	private static final long serialVersionUID = 8129816440139286101L;
	
	private short id;
	private String cod;
	private String title;

	public ProjectTypeDTO(ProjectType projectType) {
		this.id = projectType.getId();
		this.cod = projectType.getCod();
		this.title = projectType.getTitle();
	}

	public ProjectTypeDTO(short id, String cod, String title) {
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
