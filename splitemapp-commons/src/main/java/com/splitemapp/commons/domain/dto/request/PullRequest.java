package com.splitemapp.commons.domain.dto.request;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;


public class PullRequest {

	private String token;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date lastPullSuccessAt;

	/**
	 * Required by FasterXML.
	 */
	public PullRequest() {}

	public PullRequest(String token, Date lastPullSuccessAt) {
		this.token = token;
		this.lastPullSuccessAt = lastPullSuccessAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastPullSuccessAt() {
		return lastPullSuccessAt;
	}

	public void setLastPullSuccessAt(Date lastPullSuccessAt) {
		this.lastPullSuccessAt = lastPullSuccessAt;
	}

}
