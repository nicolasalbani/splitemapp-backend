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
import com.splitemapp.commons.domain.dto.request.InviteRequest;
import com.splitemapp.commons.domain.dto.response.InviteResponse;
import com.splitemapp.commons.utils.MailUtils;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.INVITE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InviteService {

	private UserEndpoint userEndpoint;
	private UserSessionEndpoint userSessionEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public InviteResponse printMessage(InviteRequest request) {

		// Creating a invite response object setting success to false by default
		InviteResponse response = new InviteResponse();
		response.setSuccess(false);

		// Retrieving user session
		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// Getting the user and message objects
			User user = userEndpoint.findById(userSession.getUser().getId());
			String email = request.getEmail();
			
			StringBuilder message = new StringBuilder();
			message.append("Hi!");
			message.append("");
			message.append("This is an invite sent to you by " +user.getFullName()+ " to start using Splitemapp!");
			message.append("You can install the application and create your free account in one step by clicking here");
			message.append("");
			message.append("Best regards,");
			message.append("The Splitemapp Team");

			// Sending the question
			MailUtils.sendMail("info","019713skull","Splitemapp invite from " +user.getFullName(), email, "info@splitemapp.com", message.toString());

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