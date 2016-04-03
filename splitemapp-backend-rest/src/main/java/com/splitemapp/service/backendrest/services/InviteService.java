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
			message.append("<html>");
			message.append("<body style=\"background-color:#E5E5E5\";\"padding:20\">");
			message.append("<table cellpadding=\"10\" align=\"center\" border=\"0\" style=\"width:50%\">");
			message.append("<tr bgcolor=\"#4EA6FF\" align=\"center\">");
			message.append("<img src=\"http://ec2-52-38-125-216.us-west-2.compute.amazonaws.com/images/splitemapp_logo.png\" style=\"width:80px;height:80px;\">");
			message.append("</tr>");
			message.append("<tr bgcolor=\"#FFFFFF\">");
			message.append("<p>Hi there!</p>");
			message.append("<p>This invite was sent to you by <b>" +user.getFullName()+ "</b> to start using <b>Splitemapp</b>!</p>");
			message.append("<p>You can install the app and create your free account in one step by clicking <a href=\"http://www.splitemapp.com\">here</a></p>");
			message.append("<i>Best regards,</i>");
			message.append("<br>");
			message.append("<i>The Splitemapp Team</i>");
			message.append("</tr>");
			message.append("</table>");
			message.append("</body>");
			message.append("</html>");

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