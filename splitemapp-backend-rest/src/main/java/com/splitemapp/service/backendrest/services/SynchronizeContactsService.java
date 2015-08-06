package com.splitemapp.service.backendrest.services;

import java.util.ArrayList;
import java.util.List;

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
import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.request.SynchronizeContactsRequest;
import com.splitemapp.commons.domain.dto.response.SynchronizeContactsResponse;
import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.endpoint.UserAvatarEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserContactDataEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;

@Service
@Path(ServiceConstants.SYNCHRONIZE_CONTACTS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SynchronizeContactsService {

	private UserEndpoint userEndpoint;
	private UserContactDataEndpoint userContactDataEndpoint;
	private UserAvatarEndpoint userAvatarEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public SynchronizeContactsResponse printMessage(SynchronizeContactsRequest request) {

		// Creating a synchronize contacts response object setting success to false by default
		SynchronizeContactsResponse synchronizeContactsResponse = new SynchronizeContactsResponse();
		synchronizeContactsResponse.setSuccess(false);

		// Setting the references for the User and UserContactData DTO lists
		List<UserDTO> userDTOList = new  ArrayList<UserDTO>();
		synchronizeContactsResponse.setUserDTOList(userDTOList);
		List<UserContactDataDTO> userContactDataDTOList = new ArrayList<UserContactDataDTO>();
		synchronizeContactsResponse.setUserContactDataDTOList(userContactDataDTOList);
		List<UserAvatarDTO> userAvatarDTOList = new ArrayList<UserAvatarDTO>();
		synchronizeContactsResponse.setUserAvatarDTOList(userAvatarDTOList);

		// Adding all found User and UserContactData information to the response list
		for(String emailAddress:request.getContactsEmailAddressList()){
			// Processing user contact data information if there is any
			UserContactData userContactData = userContactDataEndpoint.findByField(Utils.getCamelCaseName(TableField.USER_CONTACT_DATA_CONTACT_DATA), emailAddress);
			if(userContactData != null){
				userContactDataDTOList.add(new UserContactDataDTO(userContactData));
				
				// Processing user information
				User user = userContactData.getUser();
				userDTOList.add(new UserDTO(user));
				
				// Processing user avatar
				userAvatarDTOList.add(new UserAvatarDTO(user.getUserAvatars().iterator().next()));
			}
		}

		// Setting the success flag to true
		synchronizeContactsResponse.setSuccess(true);

		return synchronizeContactsResponse;
	}

	//Getters and setters
	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

	public UserContactDataEndpoint getUserContactDataEndpoint() {
		return userContactDataEndpoint;
	}

	public void setUserContactDataEndpoint(UserContactDataEndpoint userContactDataEndpoint) {
		this.userContactDataEndpoint = userContactDataEndpoint;
	}

	public UserAvatarEndpoint getUserAvatarEndpoint() {
		return userAvatarEndpoint;
	}

	public void setUserAvatarEndpoint(UserAvatarEndpoint userAvatarEndpoint) {
		this.userAvatarEndpoint = userAvatarEndpoint;
	}
}