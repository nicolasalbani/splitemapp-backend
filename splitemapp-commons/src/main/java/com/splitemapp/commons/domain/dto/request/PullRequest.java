package com.splitemapp.commons.domain.dto.request;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;


public class PullRequest {

	private String token;
	
	private boolean pullAllDates;
	
	private Long projectId;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date lastPullSuccessAt;

	/**
	 * Required by FasterXML.
	 */
	public PullRequest() {}

	public PullRequest(String token, Date lastPullSuccessAt, boolean pullAllDates, Long projectId) {
		this.token = token;
		this.lastPullSuccessAt = lastPullSuccessAt;
		this.pullAllDates = pullAllDates;
		this.projectId = projectId;
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

	public boolean isPullAllDates() {
		return pullAllDates;
	}

	public void setPullAllDates(boolean pullAllDates) {
		this.pullAllDates = pullAllDates;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
