package com.splitemapp.commons.domain.dto.response;

public class ServiceResponse {
	private Boolean success;

	public ServiceResponse() {}
	
	public ServiceResponse(Boolean success) {
		this.success = success;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
