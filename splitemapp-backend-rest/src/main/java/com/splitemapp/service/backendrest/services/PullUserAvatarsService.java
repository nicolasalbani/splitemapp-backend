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
import com.splitemapp.commons.domain.UserAvatar;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.response.PullResponse;
import com.splitemapp.service.backendrest.endpoint.UserAvatarEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PULL_USER_AVATARS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullUserAvatarsService {

	UserSessionEndpoint userSessionEndpoint;
	UserAvatarEndpoint userAvatarEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}
	
	@POST
	public PullResponse<UserAvatarDTO> printMessage(PullRequest request) throws ParseException {

		// We create a pull user contact datas response object setting success to false by default
		PullResponse<UserAvatarDTO> response = new PullResponse<UserAvatarDTO>();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			User user = userSession.getUser();

			// We set the user contact data set
			Set<UserAvatarDTO> userAvatarDTOs = new HashSet<UserAvatarDTO>();
			for(UserAvatar userAvatar:userAvatarEndpoint.findUpdatedAfter(request.getLastPullSuccessAt(), user.getId())){
				userAvatarDTOs.add(new UserAvatarDTO(userAvatar));
			}
			response.setItemSet(userAvatarDTOs);

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

	public UserAvatarEndpoint getUserAvatarEndpoint() {
		return userAvatarEndpoint;
	}

	public void setUserAvatarEndpoint(UserAvatarEndpoint userAvatarEndpoint) {
		this.userAvatarEndpoint = userAvatarEndpoint;
	}
}