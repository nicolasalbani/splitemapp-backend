package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.splitemapp.commons.domain.UserSession;

public class UserSessionDTO implements java.io.Serializable {

	private static final long serialVersionUID = 935924848642967248L;

	private Long id;
	private UserDTO userDTO;
	private String token;
	private String device;
	private String osVersion;
	private Date lastUsedAt;

	public UserSessionDTO(UserSession userSession) {
		this.userDTO = new UserDTO(userSession.getUser());
		this.token = userSession.getToken();
		this.device = userSession.getDevice();
		this.osVersion = userSession.getOsVersion();
		this.lastUsedAt = userSession.getLastUsedAt();
	}

	public UserSessionDTO(UserDTO userDTO, String token, String device, String osVersion, Date lastUsedAt) {
		this.userDTO = userDTO;
		this.token = token;
		this.device = device;
		this.osVersion = osVersion;
		this.lastUsedAt = lastUsedAt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return this.userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public Date getLastUsedAt() {
		return this.lastUsedAt;
	}

	public void setLastUsedAt(Date lastUsedAt) {
		this.lastUsedAt = lastUsedAt;
	}

}
