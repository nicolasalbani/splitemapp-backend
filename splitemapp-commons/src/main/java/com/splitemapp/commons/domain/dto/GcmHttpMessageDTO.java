package com.splitemapp.commons.domain.dto;

public class GcmHttpMessageDTO implements java.io.Serializable {

	private static final long serialVersionUID = -3129894641081934386L;

	private String[] registration_ids;
	private GcmHttpDataDTO data;

	public GcmHttpMessageDTO() {}

	public GcmHttpMessageDTO(String[] registration_ids,
			GcmHttpDataDTO data) {
		super();
		this.registration_ids = registration_ids;
		this.data = data;
	}

	public String[] getRegistration_ids() {
		return registration_ids;
	}
	public void setRegistration_ids(String[] registration_ids) {
		this.registration_ids = registration_ids;
	}
	public GcmHttpDataDTO getData() {
		return data;
	}
	public void setData(GcmHttpDataDTO data) {
		this.data = data;
	}
}
