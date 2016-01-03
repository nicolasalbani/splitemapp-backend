package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.UserSessionDTO;
import com.splitemapp.commons.domain.dto.request.PushUserSessionRequest;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;

public class UserSessionServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PUSH_USER_SESSIONS_PATH);
		return servicePathList;
	}

	@Test
	public void pushUpdateServiceTest(){
		// Creating the user list to push
		List<UserSessionDTO> itemList = new ArrayList<UserSessionDTO>();
		UserSessionDTO userSession = createUserSessionDTO(2L, 2L, "samsung GT-I9100","1234567890","Android REL-4.1.2 SDK-16","554686b8-7646-402e-9911-f28b8b417d47");
		itemList.add(userSession);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USER_SESSIONS_PATH;

		// Crafting the request object
		PushUserSessionRequest request = new PushUserSessionRequest();
		request.setLastPushSuccessAt(new Date());
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}
	
	private UserSessionDTO createUserSessionDTO(Long id, Long userId, String device, String gcmToken, String osVersion, String token){
		// Creating user DTO object.
		UserSessionDTO user = new UserSessionDTO();
		user.setId(id);
		user.setUserId(userId);
		user.setDevice(device);
		user.setGcmToken(gcmToken);
		user.setLastUsedAt(new Date());
		user.setOsVersion(osVersion);
		user.setToken(token);
		return user;
	}

}
