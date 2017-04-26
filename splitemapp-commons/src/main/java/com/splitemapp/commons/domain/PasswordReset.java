package com.splitemapp.commons.domain;

import org.joda.time.DateTime;

public class PasswordReset {
	
	private DateTime requestDate;
	private Long userId;
	
	public PasswordReset() {}
	
	public PasswordReset(DateTime requestDate, Long userId) {
		this.requestDate = requestDate;
		this.userId = userId;
	}

	public DateTime getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(DateTime requestDate) {
		this.requestDate = requestDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
