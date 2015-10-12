package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.request.PushUserRequest;
import com.splitemapp.commons.domain.dto.response.PullUserResponse;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;

public class UserServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PULL_USERS_PATH);
		servicePathList.add(ServiceConstants.PUSH_USERS_PATH);
		return servicePathList;
	}

	@Test
	public void pullServiceTest(){
		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PULL_USERS_PATH;

		// Crafting the request object
		PullRequest request = new PullRequest();
		request.setLastPullSuccessAt(new Date(100));
		request.setToken(TOKEN);

		// Making rest service call
		PullUserResponse response = RestUtils.callRestService(serviceUrl, request, PullUserResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
		
		// Validating that users linked by projects are also retrieved
		boolean hasLinkedUsers = false;
		Iterator<UserDTO> iterator = response.getItemSet().iterator();
		if(iterator.hasNext()){
			UserDTO userDTO = iterator.next();
			if(userDTO.getUsername().equals("juangomez@splitemapp.com"));
			hasLinkedUsers = true;
		}
		assertTrue(hasLinkedUsers);
	}

	@Test
	public void pushServiceTest(){
		// Creating the user list to push
		List<UserDTO> itemList = new ArrayList<UserDTO>();
		UserDTO user = createUserDTO("newtestuser2@splitemapp.com");
		itemList.add(user);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USERS_PATH;

		// Crafting the request object
		PushUserRequest request = new PushUserRequest();
		request.setLastPushSuccessAt(new Date(100));
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}

	private UserDTO createUserDTO(String userName){
		// Creating user DTO object. We always use the same ID because it will be updated on the server side
		UserDTO user = new UserDTO();
		user.setCreatedAt(new Date(1000));
		user.setCreatedIpAddress("127.0.0.1");
		user.setFullName("New Test User");
		user.setId(1L);
		user.setLastLogin(null);
		user.setLoginCnt(1);
		user.setPassword("12345");
		user.setUpdatedAt(new Date(1000));
		user.setUpdatedIpAddress("127.0.0.1");
		user.setUsername(userName);
		user.setUserStatusId((short)1);
		return user;
	}
}
