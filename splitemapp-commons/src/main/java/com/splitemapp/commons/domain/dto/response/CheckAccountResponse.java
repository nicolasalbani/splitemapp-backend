package com.splitemapp.commons.domain.dto.response;

public class CheckAccountResponse extends ServiceResponse{

	private Boolean exists;

	public CheckAccountResponse() {}

	public Boolean getExists() {
		return exists;
	}

	public void setExists(Boolean exists) {
		this.exists = exists;
	}
}
