package com.splitemapp.commons.domain.dto;


public class QuestionDTO {

	private Long userId;
	private String message;
	
	public QuestionDTO(){};
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
