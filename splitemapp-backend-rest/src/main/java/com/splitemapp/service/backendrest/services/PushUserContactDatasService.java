package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.Action;
import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.service.backendrest.endpoint.UserContactDataEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;

@Service
@Path(ServiceConstants.PUSH_USER_CONTACT_DATAS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushUserContactDatasService extends PushNotificationService{

	UserEndpoint userEndpoint;
	UserContactDataEndpoint userContactDataEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PushResponse<Long> printMessage(PushRequest<UserContactDataDTO> request) throws ParseException {
		// We create a pull groups response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();
		response.setSuccess(false);

		// Creating the pushedAt date
		Date pushedAt = new Date();

		// Defining action and details to be notified
		String action = "";

		UserSession userSession = getUserSession(request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(UserContactDataDTO userContactDataDTO:request.getItemList()){
				// Setting the action
				action = Action.UPDATE_USER_CONTACT_DATA;
				
				// We create the user contact data object
				User user = userEndpoint.findById(userContactDataDTO.getUserId());
				UserContactData userContactData = new UserContactData(user, userContactDataDTO);

				// We update the pushedAt date
				userContactData.setPushedAt(pushedAt);

				// We merge the entry to the database
				userContactDataEndpoint.merge(userContactData);
			}

			// We set the success flag and pushedAt
			response.setPushedAt(pushedAt);
			response.setSuccess(true);

			// Sending GCM notification to all related clients
			sendGcmNotification(userSession, action);
		}

		return response;
	}


	// Getters and setters

	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

	public UserContactDataEndpoint getUserContactDataEndpoint() {
		return userContactDataEndpoint;
	}

	public void setUserContactDataEndpoint(
			UserContactDataEndpoint userContactDataEndpoint) {
		this.userContactDataEndpoint = userContactDataEndpoint;
	}

}