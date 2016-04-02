package com.splitemapp.commons.domain.dto.request;

import com.splitemapp.commons.domain.dto.QuestionDTO;

public class QuestionsRequest {

	private QuestionDTO questionDTO;
	private String token;

	/**
	 * Required by FasterXML.
	 */
	public QuestionsRequest() {}

	public QuestionsRequest(QuestionDTO questionDTO, String token) {
		this.setQuestionDTO(questionDTO);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public QuestionDTO getQuestionDTO() {
		return questionDTO;
	}

	public void setQuestionDTO(QuestionDTO questionDTO) {
		this.questionDTO = questionDTO;
	}
}
