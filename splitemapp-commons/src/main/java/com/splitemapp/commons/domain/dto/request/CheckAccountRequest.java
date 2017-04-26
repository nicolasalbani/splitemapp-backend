package com.splitemapp.commons.domain.dto.request;

public class CheckAccountRequest {

	private String username;

	/**
	 * Required by FasterXML.
	 */
	public CheckAccountRequest() {}

	public CheckAccountRequest(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
