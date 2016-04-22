package com.splitemapp.service.backendrest.services;

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
import com.splitemapp.commons.domain.dto.request.LogoutRequest;
import com.splitemapp.commons.domain.dto.response.ServiceResponse;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.LOGOUT_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogoutService {

	private static Logger logger = Logger.getLogger(LogoutService.class);

	private UserSessionEndpoint userSessionEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public ServiceResponse printMessage(LogoutRequest request) {
		// Service start time
		DateTime serviceStartTime = new DateTime();

		// We create a logout response object setting success to false by default
		ServiceResponse response = new ServiceResponse();

		// We look for the user
		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		// If we found the user session then we remove it
		if(userSession!=null){
			userSessionEndpoint.remove(userSession);
			response.setSuccess(true);
		}

		// Calculating service time
		logger.info(getClass().getSimpleName() +" time was: "+ (new DateTime().getMillis()-serviceStartTime.getMillis()+ "ms"));

		return response;
	}

	//Getters and setters
	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}

}