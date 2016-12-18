package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class UserDTO implements java.io.Serializable {

	private static final long serialVersionUID = 8018834364942997890L;

	private Long id;
	private Short userStatusId;
	private String username;
	private String password;
	private String fullName;
	private int loginCnt;
	private String createdIpAddress;
	private String updatedIpAddress;

	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date lastLogin;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date createdAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date updatedAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date pushedAt;
	
	public UserDTO(){}

	public UserDTO(User user) {
		this.id = user.getId();
		this.userStatusId = user.getUserStatus().getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.fullName = user.getFullName();
		this.lastLogin = user.getLastLogin();
		this.loginCnt = user.getLoginCnt();
		this.createdAt = user.getCreatedAt();
		this.createdIpAddress = user.getCreatedIpAddress();
		this.updatedAt = user.getUpdatedAt();
		this.updatedIpAddress = user.getUpdatedIpAddress();
		this.pushedAt = user.getPushedAt();
	}

	public UserDTO(User user, boolean includePassword) {
		this.id = user.getId();
		this.userStatusId = user.getUserStatus().getId();
		this.username = user.getUsername();
		if(includePassword){
			this.password = user.getPassword();
		}
		this.fullName = user.getFullName();
		this.lastLogin = user.getLastLogin();
		this.loginCnt = user.getLoginCnt();
		this.createdAt = user.getCreatedAt();
		this.createdIpAddress = user.getCreatedIpAddress();
		this.updatedAt = user.getUpdatedAt();
		this.updatedIpAddress = user.getUpdatedIpAddress();
		this.pushedAt = user.getPushedAt();
	}

	public UserDTO(Short userStatusId, String username, String password,
			String fullName, String lastName, Date lastLogin, int loginCnt,
			Date createdAt, String createdIpAddress, Date updatedAt, Date pushedAt,
			String updatedIpAddress) {
		this.userStatusId = userStatusId;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.lastLogin = lastLogin;
		this.loginCnt = loginCnt;
		this.createdAt = createdAt;
		this.createdIpAddress = createdIpAddress;
		this.updatedAt = updatedAt;
		this.pushedAt = pushedAt;
		this.updatedIpAddress = updatedIpAddress;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getUserStatusId() {
		return this.userStatusId;
	}

	public void setUserStatusId(Short userStatusId) {
		this.userStatusId = userStatusId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getLoginCnt() {
		return this.loginCnt;
	}

	public void setLoginCnt(int loginCnt) {
		this.loginCnt = loginCnt;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedIpAddress() {
		return this.createdIpAddress;
	}

	public void setCreatedIpAddress(String createdIpAddress) {
		this.createdIpAddress = createdIpAddress;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getPushedAt() {
		return pushedAt;
	}

	public void setPushedAt(Date pushedAt) {
		this.pushedAt = pushedAt;
	}

	public String getUpdatedIpAddress() {
		return this.updatedIpAddress;
	}

	public void setUpdatedIpAddress(String updatedIpAddress) {
		this.updatedIpAddress = updatedIpAddress;
	}

}
