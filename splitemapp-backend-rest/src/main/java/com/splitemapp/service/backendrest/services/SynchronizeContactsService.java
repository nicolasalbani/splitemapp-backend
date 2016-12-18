package com.splitemapp.service.backendrest.services;

import java.util.ArrayList;
import java.util.List;

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
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.request.SynchronizeContactsRequest;
import com.splitemapp.commons.domain.dto.response.SynchronizeContactsResponse;
import com.splitemapp.commons.utils.TimeUtils;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.SYNCHRONIZE_CONTACTS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SynchronizeContactsService {
	
	private static Logger logger = Logger.getLogger(SynchronizeContactsService.class);

	private UserEndpoint userEndpoint;
	private UserSessionEndpoint userSessionEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public SynchronizeContactsResponse printMessage(SynchronizeContactsRequest request) {
		// Service start time
		DateTime serviceStartTime = new DateTime();

		// Creating a synchronize contacts response object setting success to false by default
		SynchronizeContactsResponse response = new SynchronizeContactsResponse();
		response.setPulledAt(TimeUtils.getUTCDate());

		// Retrieving user session
		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// Setting the references for the User and UserContactData DTO lists
			List<UserDTO> userDTOList = new  ArrayList<UserDTO>();
			response.setUserDTOList(userDTOList);
			List<UserContactDataDTO> userContactDataDTOList = new ArrayList<UserContactDataDTO>();
			response.setUserContactDataDTOList(userContactDataDTOList);
			List<UserAvatarDTO> userAvatarDTOList = new ArrayList<UserAvatarDTO>();
			response.setUserAvatarDTOList(userAvatarDTOList);

			// Adding all found User and UserContactData information to the response list
			for(String emailAddress:request.getContactsEmailAddressList()){
				// Processing user contact data information if there is any
				User user = userEndpoint.findUserForSyncContacts(TableField.USER_USERNAME, emailAddress);
				if(user != null){
					userContactDataDTOList.add(new UserContactDataDTO(user.getUserContactDatas().iterator().next()));

					// Processing user information
					userDTOList.add(new UserDTO(user,false));

					// Processing user avatar
					userAvatarDTOList.add(new UserAvatarDTO(user.getUserAvatars().iterator().next()));
				}
			}

			// Setting the success flag to true
			response.setSuccess(true);
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

	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}
}