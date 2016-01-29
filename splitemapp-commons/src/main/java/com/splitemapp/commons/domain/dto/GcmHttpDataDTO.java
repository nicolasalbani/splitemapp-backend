package com.splitemapp.commons.domain.dto;

public class GcmHttpDataDTO implements java.io.Serializable {

	private static final long serialVersionUID = -3129894641081934386L;

	private String sender;
	private String action;
	private String projectId;
	private String details;

	public GcmHttpDataDTO() {}
	
	public GcmHttpDataDTO(String sender, String action, String projectId, String details) {
		this.sender = sender;
		this.setProjectId(projectId);
		this.action = action;
		this.details = details;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}
