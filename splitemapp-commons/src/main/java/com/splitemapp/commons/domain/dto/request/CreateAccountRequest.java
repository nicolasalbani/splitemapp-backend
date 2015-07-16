package com.splitemapp.commons.domain.dto.request;

public class CreateAccountRequest {

	private String fullName;
	private String ipAddress;
	private String password;
	private String email;
	private byte[] avatar;

	/**
	 * Required by FasterXML.
	 */
	public CreateAccountRequest() {}

	public CreateAccountRequest(String fullName, String ipAddress, String password, String email, byte[] avatar) {
		this.fullName = fullName;
		this.ipAddress = ipAddress;
		this.password = password;
		this.email = email;
		this.setAvatar(avatar);
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

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

}
