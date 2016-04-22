package com.splitemapp.service.backendrest.services;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserSessionDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PUSH_USER_SESSIONS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushUserSessionService {

	private static Logger logger = Logger.getLogger(PushUserSessionService.class);

	UserSessionEndpoint userSessionEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PushResponse<Long> printMessage(PushRequest<UserSessionDTO> request) throws ParseException {
		// Service start time
		DateTime serviceStartTime = new DateTime();

		// We create a response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(UserSessionDTO userSessionDTO:request.getItemList()){
				// We create the user object
				UserSession sentUserSession = userSessionEndpoint.findById(userSessionDTO.getId());
				sentUserSession.setGcmToken(userSessionDTO.getGcmToken());

				// We merge the entry to the database
				userSessionEndpoint.merge(sentUserSession);
			}

			response.setSuccess(true);
		}

		// Calculating service time
		logger.info(getClass().getSimpleName() +" time was: "+ (new DateTime().getMillis()-serviceStartTime.getMillis()+ "ms"));

		return response;
	}


	// Getters and setters

	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}

}