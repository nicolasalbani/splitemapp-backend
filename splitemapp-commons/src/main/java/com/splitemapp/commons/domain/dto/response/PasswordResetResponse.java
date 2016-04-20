package com.splitemapp.commons.domain.dto.response;


public class PasswordResetResponse extends ServiceResponse{

	private Boolean mailFound;

	public PasswordResetResponse() {}

	public Boolean getMailFound() {
		return mailFound;
	}

	public void setMailFound(Boolean mailFound) {
		this.mailFound = mailFound;
	}

}
