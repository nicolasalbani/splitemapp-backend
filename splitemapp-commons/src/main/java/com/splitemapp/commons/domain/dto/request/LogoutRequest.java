package com.splitemapp.commons.domain.dto.request;

public class LogoutRequest {

	private String token;

	/**
	 * Required by FasterXML.
	 */
	public LogoutRequest() {}

	public LogoutRequest(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
