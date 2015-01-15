package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

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
import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.response.PullResponse;
import com.splitemapp.service.backendrest.endpoint.UserContactDataEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PULL_USER_CONTACT_DATAS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullUserContactDatasService {

	UserSessionEndpoint userSessionEndpoint;
	UserContactDataEndpoint userContactDataEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PullResponse<UserContactDataDTO> printMessage(PullRequest request) throws ParseException {

		// We create a pull user contact datas response object setting success to false by default
		PullResponse<UserContactDataDTO> response = new PullResponse<UserContactDataDTO>();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			User user = userSession.getUser();

			// We set the user contact data set
			Set<UserContactDataDTO> userContactDataDTOs = new HashSet<UserContactDataDTO>();
			for(UserContactData userContactData:userContactDataEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				userContactDataDTOs.add(new UserContactDataDTO(userContactData));
			}
			response.setItemSet(userContactDataDTOs);

			// We set the success flag
			response.setSuccess(true);
		}

		return response;
	}


	// Getters and setters
	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}

	public UserContactDataEndpoint getUserContactDataEndpoint() {
		return userContactDataEndpoint;
	}

	public void setUserContactDataEndpoint(
			UserContactDataEndpoint userContactDataEndpoint) {
		this.userContactDataEndpoint = userContactDataEndpoint;
	}
}