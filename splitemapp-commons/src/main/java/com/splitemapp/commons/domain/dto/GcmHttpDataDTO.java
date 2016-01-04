package com.splitemapp.commons.domain.dto;

public class GcmHttpDataDTO implements java.io.Serializable {

	private static final long serialVersionUID = -3129894641081934386L;

	private String pushServiceCalled;

	public GcmHttpDataDTO() {}
	
	public GcmHttpDataDTO(String pushServiceCalled) {
		this.pushServiceCalled = pushServiceCalled;
	}

	public String getPushServiceCalled() {
		return pushServiceCalled;
	}

	public void setPushServiceCalled(String pushServiceCalled) {
		this.pushServiceCalled = pushServiceCalled;
	}
}
