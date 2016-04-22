package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.Action;
import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserAvatar;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.utils.TimeUtils;
import com.splitemapp.service.backendrest.endpoint.UserAvatarEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;

@Service
@Path(ServiceConstants.PUSH_USER_AVATARS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushUserAvatarsService extends PushNotificationService{

	private static Logger logger = Logger.getLogger(PushUserAvatarsService.class);

	UserEndpoint userEndpoint;
	UserAvatarEndpoint userAvatarEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PushResponse<Long> printMessage(PushRequest<UserAvatarDTO> request) throws ParseException {
		// Service start time
		DateTime serviceStartTime = new DateTime();

		// We create a pull groups response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();

		// Creating the pushedAt date
		Date pushedAt = TimeUtils.getUTCDate();

		// Defining action and details to be notified
		String action = "";

		UserSession userSession = getUserSession(request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(UserAvatarDTO userAvatarDTO:request.getItemList()){
				// Setting the action
				action = Action.UPDATE_USER_AVATAR;

				// We create the user avatar object
				User user = userEndpoint.findById(userAvatarDTO.getUserId());
				User updatedBy = userEndpoint.findById(userAvatarDTO.getUpdatedBy());
				User pushedBy = userEndpoint.findById(userAvatarDTO.getPushedBy());
				UserAvatar userAvatar = new UserAvatar(user,updatedBy,pushedBy,userAvatarDTO);

				// We update the pushedAt date
				userAvatar.setPushedAt(pushedAt);

				// We merge the entry to the database
				userAvatarEndpoint.merge(userAvatar);
			}

			// We set the success flag and pushedAt
			response.setPushedAt(pushedAt);
			response.setSuccess(true);

			// Sending GCM notification to all related clients
			sendGcmNotification(userSession, action, null, null);
		}

		// Calculating service time
		logger.info(getClass().getSimpleName() +" time was: "+ (new DateTime().getMillis()-serviceStartTime.getMillis()+ "ms"));

		return response;
	}


	// Getters and setters

	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

	public UserAvatarEndpoint getUserAvatarEndpoint() {
		return userAvatarEndpoint;
	}

	public void setUserAvatarEndpoint(UserAvatarEndpoint userAvatarEndpoint) {
		this.userAvatarEndpoint = userAvatarEndpoint;
	}

}