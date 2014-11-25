package com.splitemapp.commons.domain.dto;

import com.splitemapp.commons.domain.UserToProjectStatus;

public class UserToProjectStatusDTO implements java.io.Serializable {

	private static final long serialVersionUID = -8633833853020106879L;

	private Short id;
	private String cod;
	private String title;
	
	public UserToProjectStatusDTO(){}

	public UserToProjectStatusDTO(UserToProjectStatus userToProjectStatus) {
		this.id = userToProjectStatus.getId();
		this.cod = userToProjectStatus.getCod();
		this.title = userToProjectStatus.getTitle();
	}

	public UserToProjectStatusDTO(Short id, String cod, String title) {
		this.id = id;
		this.cod = cod;
		this.title = title;
	}

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
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
