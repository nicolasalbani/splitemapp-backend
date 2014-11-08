package com.splitemapp.service.domainmodel.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class LoginResponse {

	private Boolean success;
	private String sessionToken;
	private Boolean changePassword;
	
	public LoginResponse() {}
	
	public LoginResponse(Boolean success,String sessionToken,Boolean changePassword) {
		this.setSuccess(success);
		this.sessionToken = sessionToken;
		this.changePassword	= changePassword;	
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public Boolean getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Boolean changePassword) {
		this.changePassword = changePassword;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
