package com.splitemapp.commons.domain.dto;

import com.splitemapp.commons.domain.GroupStatus;

public class GroupStatusDTO implements java.io.Serializable {

	private static final long serialVersionUID = 8700853389002247470L;
	
	private short id;
	private String cod;
	private String title;
	
	public GroupStatusDTO(GroupStatus groupStatus) {
		this.id = groupStatus.getId();
		this.cod = groupStatus.getCod();
		this.title = groupStatus.getTitle();
	}
	
	public GroupStatusDTO(short id, String cod, String title) {
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