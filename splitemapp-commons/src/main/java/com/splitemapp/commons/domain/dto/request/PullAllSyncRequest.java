package com.splitemapp.commons.domain.dto.request;

import java.util.Date;

public class PullAllSyncRequest {

	private String token;
	private Date lastPullSuccessAt;

	/**
	 * Required by FasterXML.
	 */
	public PullAllSyncRequest() {}

	public PullAllSyncRequest(String token, Date lastPullSuccessAt) {
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
