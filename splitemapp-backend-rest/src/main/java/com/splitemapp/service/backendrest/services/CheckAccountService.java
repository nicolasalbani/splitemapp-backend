package com.splitemapp.service.backendrest.services;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.dto.request.CheckAccountRequest;
import com.splitemapp.commons.domain.dto.response.CheckAccountResponse;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Service
@Path(ServiceConstants.CHECK_ACCOUNT_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CheckAccountService {

	private static Logger logger = Logger.getLogger(CheckAccountService.class);

	private UserEndpoint userEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public CheckAccountResponse printMessage(CheckAccountRequest request) {
		// Service start time
		DateTime serviceStartTime = new DateTime();

		// We create a check account response object setting exists to false by default
		CheckAccountResponse response = new CheckAccountResponse();

		// We look for the user
		User user = userEndpoint.findUserForLogin(TableField.USER_USERNAME, request.getUsername());

		// If we found the user then we validate
		response.setSuccess(true);
		response.setMessage("");
		if(user!=null){
			response.setExists(true);
		} else {
			response.setExists(false);
		}

		// Calculating service time
		logger.info(getClass().getSimpleName() +" time was: "+ (new DateTime().getMillis()-serviceStartTime.getMillis()+ "ms"));

		return response;
	}

	//Getters and setters
	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

}