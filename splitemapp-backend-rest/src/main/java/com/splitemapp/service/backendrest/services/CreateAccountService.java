package com.splitemapp.service.backendrest.services;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.constants.TableFieldCod;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.commons.domain.UserStatus;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.UserStatusDTO;
import com.splitemapp.commons.domain.dto.request.CreateAccountRequest;
import com.splitemapp.commons.domain.dto.response.CreateAccountResponse;
import com.splitemapp.service.backendrest.endpoint.UserContactDataEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserStatusEndpoint;

@Service
@Path(ServiceConstants.CREATE_ACCOUNT_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateAccountService {

	private UserStatusEndpoint userStatusEndpoint;
	private UserEndpoint userEndpoint;
	private UserContactDataEndpoint userContactDataEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}
	
	@POST
	public CreateAccountResponse printMessage(CreateAccountRequest request) {

		// We create a create account response object setting success to false by default
		CreateAccountResponse response = new CreateAccountResponse();
		response.setSuccess(false);

		// We check if the user already exists
		User user = userEndpoint.findByField(TableField.USER_USERNAME, request.getUsername());

		// If we did not find the user then we create it
		if(user==null){
			// We get the active user status
			UserStatus userStatus = userStatusEndpoint.findByField(TableField.ALTER_TABLE_COD, TableFieldCod.USER_STATUS_ACTIVE);

			// We create and persist the user instance
			User newUser = new User();
			newUser.setCreatedAt(new Date());
			newUser.setCreatedIpAddress(request.getIpAddress());
			newUser.setUpdatedAt(new Date());
			newUser.setUpdatedIpAddress(request.getIpAddress());
			newUser.setPassword(request.getPassword());
			newUser.setUsername(request.getUsername());
			//TODO We need to change the getUsername for the actual first name. Add first and last in the create account page?
			newUser.setFirstName(request.getUsername());
			newUser.setUserStatus(userStatus);
			userEndpoint.persist(newUser);

			// We create and persist the user contact data
			UserContactData userContactData = new UserContactData();
			userContactData.setContactData(request.getEmail());
			userContactData.setCreatedAt(new Date());
			userContactData.setUser(newUser);
			userContactDataEndpoint.persist(userContactData);

			// We set the response values
			response.setSuccess(true);
			response.setUserContactDataDTO(new UserContactDataDTO(userContactData));
			response.setUserDTO(new UserDTO(newUser));
			response.setUserStatusDTO(new UserStatusDTO(userStatus));
		} else {
			//TODO check the status of the user, if it's disabled we can enable it?
		}

		return response;
	}
	
	// Getters and setters

	public UserStatusEndpoint getUserStatusEndpoint() {
		return userStatusEndpoint;
	}

	public void setUserStatusEndpoint(UserStatusEndpoint userStatusEndpoint) {
		this.userStatusEndpoint = userStatusEndpoint;
	}

	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

	public UserContactDataEndpoint getUserContactDataEndpoint() {
		return userContactDataEndpoint;
	}

	public void setUserContactDataEndpoint(
			UserContactDataEndpoint userContactDataEndpoint) {
		this.userContactDataEndpoint = userContactDataEndpoint;
	}
}