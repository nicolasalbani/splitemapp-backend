package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserInvite;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserInviteDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.response.PullResponse;
import com.splitemapp.commons.utils.TimeUtils;
import com.splitemapp.service.backendrest.endpoint.UserInviteEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PULL_USER_INVITES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullUserInvitesService {

	private static Logger logger = Logger.getLogger(PullUserInvitesService.class);

	UserSessionEndpoint userSessionEndpoint;
	UserInviteEndpoint userInviteEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PullResponse<UserInviteDTO> printMessage(PullRequest request) throws ParseException {
		// Service start time
		DateTime serviceStartTime = new DateTime();

		// We create a pull all sync response object setting success to false by default
		PullResponse<UserInviteDTO> response = new PullResponse<UserInviteDTO>();

		// Creating the pulledAt date
		Date pulledAt = TimeUtils.getUTCDate();

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			User user = userSession.getUser();

			// We set the user invite set
			Set<UserInviteDTO> userInviteDTOs = new HashSet<UserInviteDTO>();
			for(UserInvite userInvite:userInviteEndpoint.findPushedAfterByUser(request.getLastPullSuccessAt(), user.getId())){
				userInviteDTOs.add(new UserInviteDTO(userInvite));
			}
			response.setItemSet(userInviteDTOs);

			// We set the success flag
			response.setPulledAt(pulledAt);
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

	public UserInviteEndpoint getUserInviteEndpoint() {
		return userInviteEndpoint;
	}

	public void setUserInviteEndpoint(UserInviteEndpoint userInviteEndpoint) {
		this.userInviteEndpoint = userInviteEndpoint;
	}

}