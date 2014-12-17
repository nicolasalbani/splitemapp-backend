package com.splitemapp.commons.domain.dto.request;



public class PullAllSyncRequest {

	private String token;
	private String lastPullSuccessAt;

	/**
	 * Required by FasterXML.
	 */
	public PullAllSyncRequest() {}

	public PullAllSyncRequest(String token, String lastPullSuccessAt) {
		this.token = token;
		this.lastPullSuccessAt = lastPullSuccessAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLastPullSuccessAt() {
		return lastPullSuccessAt;
	}

	public void setLastPullSuccessAt(String lastPullSuccessAt) {
		this.lastPullSuccessAt = lastPullSuccessAt;
	}

}
