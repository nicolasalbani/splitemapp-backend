package com.splitemapp.commons.domain.dto;

import com.splitemapp.commons.domain.UserToGroupStatus;


public class UserToGroupStatusDTO implements java.io.Serializable {

	private static final long serialVersionUID = -1355846727766418533L;

	private Short id;
	private String cod;
	private String title;
	
	public UserToGroupStatusDTO(){}

	public UserToGroupStatusDTO(UserToGroupStatus userToGroupStatus) {
		this.id = userToGroupStatus.getId();
		this.cod = userToGroupStatus.getCod();
		this.title = userToGroupStatus.getTitle();
	}

	public UserToGroupStatusDTO(Short id, String cod, String title) {
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
