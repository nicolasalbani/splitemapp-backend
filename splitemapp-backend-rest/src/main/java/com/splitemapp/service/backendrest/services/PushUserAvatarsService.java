package com.splitemapp.service.backendrest.services;

import java.text.ParseException;

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
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.endpoint.UserAvatarEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PUSH_USER_AVATARS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushUserAvatarsService {

	UserSessionEndpoint userSessionEndpoint;
	UserEndpoint userEndpoint;
	UserAvatarEndpoint userAvatarEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}
	
	@POST
	public PushResponse<Long> printMessage(PushRequest<UserAvatarDTO> request) throws ParseException {

		// We create a pull groups response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(UserAvatarDTO userAvatarDTO:request.getItemList()){
				// We create the user avatar object
				User user = userEndpoint.findById(userAvatarDTO.getUserId());
				UserAvatar userAvatar = new UserAvatar(user, userAvatarDTO);
				
				if(Utils.isDateAfter(userAvatarDTO.getCreatedAt(),request.getLastPushSuccessAt())){
					// We persist the entry to the database
					userAvatar.setId(null);
					userAvatarEndpoint.persist(userAvatar);
					
					// We add the IdUpdate element to the response list
					response.getIdUpdateList().add(new IdUpdate<Long>(userAvatarDTO.getId(), userAvatar.getId()));
				} else {
					// We merge the entry to the database
					userAvatarEndpoint.merge(userAvatar);
				}
			}

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