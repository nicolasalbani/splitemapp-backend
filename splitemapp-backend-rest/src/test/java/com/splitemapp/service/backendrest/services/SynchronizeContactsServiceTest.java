package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.request.SynchronizeContactsRequest;
import com.splitemapp.commons.domain.dto.response.SynchronizeContactsResponse;
import com.splitemapp.commons.rest.RestUtils;

public class SynchronizeContactsServiceTest extends BaseServiceTest {

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.SYNCHRONIZE_CONTACTS_PATH);
		return servicePathList;
	}

	@Test
	public void synchronizeContactsServiceTest(){
		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.SYNCHRONIZE_CONTACTS_PATH;

		// Creating contacts email address list
		List<String> contactsEmailAddressList = new ArrayList<String>();
		contactsEmailAddressList.add("juangomez@splitemapp.com");
		
		// Crafting the request object
		SynchronizeContactsRequest request = new SynchronizeContactsRequest();
		request.setContactsEmailAddressList(contactsEmailAddressList);
		request.setToken(TOKEN);

		// Making rest service call
		SynchronizeContactsResponse response = RestUtils.callRestService(serviceUrl, request, SynchronizeContactsResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());

		// Validating userDTOList retrieved
		boolean userOK = false;
		List<UserDTO> userDTOList = response.getUserDTOList();
		for(UserDTO userDTO:userDTOList){
			if(userDTO.getUsername().equalsIgnoreCase("juangomez@splitemapp.com")){
				userOK = true;
			}
		}
		assertTrue("Expected User was not returned!", userOK);
		
		// Validating userAvatarDTOList retrieved
		boolean userAvatarOK = false;
		List<UserAvatarDTO> userAvatarDTOList = response.getUserAvatarDTOList();
		for(UserAvatarDTO userAvatarDTO:userAvatarDTOList){
			if(userAvatarDTO.getAvatarData()[0] == 0){
				userAvatarOK = true;
			}
		}
		assertTrue("Expected UserAvatar was not returned!", userAvatarOK);
		
		// Validating userContactDataDTOList retrieved
		boolean userContactDataOK = false;
		List<UserContactDataDTO> userContactDataDTOList = response.getUserContactDataDTOList();
		for(UserContactDataDTO userContactDataDTO:userContactDataDTOList){
			if(userContactDataDTO.getContactData().equalsIgnoreCase("juangomez@splitemapp.com")){
				userContactDataOK = true;
			}
		}
		assertTrue("Expected UserContactData was not returned!", userContactDataOK);
	}
}
