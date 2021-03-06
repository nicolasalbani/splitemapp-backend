package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.UserContactDataDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.request.PushUserContactDataRequest;
import com.splitemapp.commons.domain.dto.response.PullUserContactDataResponse;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;
import com.splitemapp.commons.utils.TimeUtils;

public class UserContactDataServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PULL_USER_CONTACT_DATAS_PATH);
		servicePathList.add(ServiceConstants.PUSH_USER_CONTACT_DATAS_PATH);
		return servicePathList;
	}

	@Test
	public void pullServiceTest(){
		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PULL_USER_CONTACT_DATAS_PATH;

		// Crafting the request object
		PullRequest request = new PullRequest();
		request.setLastPullSuccessAt(new Date(100));
		request.setToken(TOKEN);

		// Making rest service call
		PullUserContactDataResponse response = RestUtils.callRestService(serviceUrl, request, PullUserContactDataResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());

		// Validating that users linked by projects are also retrieved
		boolean hasLinkedUserContactData = false;
		Iterator<UserContactDataDTO> iterator = response.getItemSet().iterator();
		while(iterator.hasNext()){
			UserContactDataDTO userContactDataDTO = iterator.next();
			if(userContactDataDTO.getContactData().equals("juangomez@splitemapp.com")){
				hasLinkedUserContactData = true;
			} else if(userContactDataDTO.getContactData().equals("juanperetti@splitemapp.com")){
				Assert.fail("Should not be obtaining a user from an unlinked project");
			}
		}
		assertTrue(hasLinkedUserContactData);
	}

	@Test
	public void pushCreateServiceTest(){
		// Creating the user list to push
		List<UserContactDataDTO> itemList = new ArrayList<UserContactDataDTO>();
		UserContactDataDTO userContactData = createUserContactDataDTO(4l,10l,"newtestuser@splitemapp.com");
		itemList.add(userContactData);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USER_CONTACT_DATAS_PATH;

		// Crafting the request object
		PushUserContactDataRequest request = new PushUserContactDataRequest();
		request.setLastPushSuccessAt(new Date(100));
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}
	
	@Test
	public void pushUpdateServiceTest(){
		// Creating the user list to push
		List<UserContactDataDTO> itemList = new ArrayList<UserContactDataDTO>();
		UserContactDataDTO userContactData = createUserContactDataDTO(3l,3l,"updateduser@splitemapp.com");
		itemList.add(userContactData);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USER_CONTACT_DATAS_PATH;

		// Crafting the request object
		PushUserContactDataRequest request = new PushUserContactDataRequest();
		request.setLastPushSuccessAt(TimeUtils.getUTCDate());
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}

	private UserContactDataDTO createUserContactDataDTO(Long id, Long userId, String userName){
		// Creating user contact data DTO object
		UserContactDataDTO userContactDataDTO = new UserContactDataDTO();
		userContactDataDTO.setId(id);
		userContactDataDTO.setUserId(userId);
		userContactDataDTO.setContactData(userName);
		userContactDataDTO.setVerified(true);
		userContactDataDTO.setVerifiedAt(new Date(100));
		userContactDataDTO.setCreatedAt(new Date(100));
		userContactDataDTO.setUpdatedAt(new Date(100));
		userContactDataDTO.setUpdatedBy(userId);
		return userContactDataDTO;
	}
}
