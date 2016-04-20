package com.splitemapp.commons.domain.dto.request;

public class PasswordResetRequest {

	private String username;

	/**
	 * Required by FasterXML.
	 */
	public PasswordResetRequest() {}

	public PasswordResetRequest(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
