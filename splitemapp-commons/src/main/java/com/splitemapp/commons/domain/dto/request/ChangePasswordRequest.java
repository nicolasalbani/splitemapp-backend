package com.splitemapp.commons.domain.dto.request;

public class ChangePasswordRequest {

	private String token;
	private String currentPassword;
	private String newPassword;

	/**
	 * Required by FasterXML.
	 */
	public ChangePasswordRequest() {}

	public ChangePasswordRequest(String token, String currentPassword, String newPassword) {
		this.token = token;
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
