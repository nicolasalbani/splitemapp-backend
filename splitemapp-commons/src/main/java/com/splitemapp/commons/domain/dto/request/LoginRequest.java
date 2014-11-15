package com.splitemapp.commons.domain.dto.request;

public class LoginRequest {

	private String username;
	private String password;
	private String device;
	private String osVersion;
	
	/**
	 * Required by FasterXML.
	 */
	public LoginRequest() {}
	
	public LoginRequest(String username, String password, String device, String osVersion) {
		this.username      		= username;
		this.password     		= password;
		this.device     		= device;
		this.osVersion			= osVersion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
}
