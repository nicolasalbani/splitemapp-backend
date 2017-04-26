package com.splitemapp.commons.domain.dto.request;

public class CreateAccountRequest {

	private String fullName;
	private String ipAddress;
	private String password;
	private String email;
	private String avatarUrl;

	/**
	 * Required by FasterXML.
	 */
	public CreateAccountRequest() {}

	public CreateAccountRequest(String fullName, String ipAddress, String password, String email, String avatarUrl) {
		this.fullName = fullName;
		this.ipAddress = ipAddress;
		this.password = password;
		this.email = email;
		this.avatarUrl = avatarUrl;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

}
