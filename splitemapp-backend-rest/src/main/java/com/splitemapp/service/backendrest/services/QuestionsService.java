package com.splitemapp.service.backendrest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.request.QuestionsRequest;
import com.splitemapp.commons.domain.dto.response.QuestionsResponse;
import com.splitemapp.commons.utils.MailUtils;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.QUESTIONS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionsService {

	private UserEndpoint userEndpoint;
	private UserSessionEndpoint userSessionEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public QuestionsResponse printMessage(QuestionsRequest request) {

		// Creating a synchronize contacts response object setting success to false by default
		QuestionsResponse response = new QuestionsResponse();

		// Retrieving user session
		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// Getting the user and message objects
			User user = userEndpoint.findById(userSession.getUser().getId());
			String message = request.getQuestionDTO().getMessage();

			// Sending the question
			MailUtils.sendMail("questions","019713skull","Question from " +user.getFullName(), user.getUsername(),message);

			// Setting the success flag to true
			response.setSuccess(true);
		}

		return response;
	}

	//Getters and setters
	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}
}