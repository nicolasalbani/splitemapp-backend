package com.splitemapp.commons.domain.dto;

import com.splitemapp.commons.domain.UserStatus;


public class UserStatusDTO implements java.io.Serializable {

	private static final long serialVersionUID = 4184361611600678462L;

	private short id;
	private String cod;
	private String title;
	
	public UserStatusDTO(){}

	public UserStatusDTO(UserStatus userStatus) {
		this.id = userStatus.getId();
		this.cod = userStatus.getCod();
		this.title = userStatus.getTitle();
	}

	public UserStatusDTO(short id, String cod, String title) {
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
