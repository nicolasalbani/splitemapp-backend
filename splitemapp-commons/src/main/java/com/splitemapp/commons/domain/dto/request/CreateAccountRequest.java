package com.splitemapp.commons.domain.dto.request;

public class CreateAccountRequest {

	private String username;
	private String ipAddress;
	private String password;
	private String email;
	private byte[] avatar;

	/**
	 * Required by FasterXML.
	 */
	public CreateAccountRequest() {}

	public CreateAccountRequest(String username, String ipAddress, String password, String email, byte[] avatar) {
		this.username = username;
		this.ipAddress = ipAddress;
		this.password = password;
		this.email = email;
		this.setAvatar(avatar);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
