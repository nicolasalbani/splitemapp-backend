package com.splitemapp.commons.domain.dto.response;

import com.splitemapp.commons.constants.ServiceConstants;

public class ServiceResponse {
	private Boolean success;
	private String message;

	public ServiceResponse() {
		this.success = false;
		this.message = ServiceConstants.ERROR_MESSAGE_SERVER_ERROR;
	}
	
	public ServiceResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
