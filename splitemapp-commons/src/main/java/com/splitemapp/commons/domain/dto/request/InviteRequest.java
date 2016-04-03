package com.splitemapp.commons.domain.dto.request;


public class InviteRequest {

	private String email;
	private String token;

	/**
	 * Required by FasterXML.
	 */
	public InviteRequest() {}

	public InviteRequest(String email, String token) {
		this.setEmail(email);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
