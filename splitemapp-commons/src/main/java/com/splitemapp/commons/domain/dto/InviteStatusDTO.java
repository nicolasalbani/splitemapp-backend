package com.splitemapp.commons.domain.dto;

import com.splitemapp.commons.domain.InviteStatus;

public class InviteStatusDTO implements java.io.Serializable {

	private static final long serialVersionUID = -1943633347091729409L;
	
	private short id;
	private String cod;
	private String title;

	public InviteStatusDTO(InviteStatus inviteStatus) {
		this.id = inviteStatus.getId();
		this.cod = inviteStatus.getCod();
		this.title = inviteStatus.getTitle();
	}

	public InviteStatusDTO(short id, String cod, String title) {
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
