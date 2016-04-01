package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.UserDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.request.PushUserRequest;
import com.splitemapp.commons.domain.dto.response.PullUserResponse;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;
import com.splitemapp.commons.utils.TimeUtils;

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
		while(iterator.hasNext()){
			UserDTO userDTO = iterator.next();
			if(userDTO.getUsername().equals("juangomez@splitemapp.com")){
				hasLinkedUsers = true;
			} else if(userDTO.getUsername().equals("juanperetti@splitemapp.com")){
				Assert.fail("Should not be obtaining a user from an unlinked project");
			}
		}
		assertTrue(hasLinkedUsers);
	}

	@Test
	public void pushCreateServiceTest(){
		// Creating the user list to push
		List<UserDTO> itemList = new ArrayList<UserDTO>();
		UserDTO user = createUserDTO(4L, "newtestuser@splitemapp.com", "New Test User");
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
	
	@Test
	public void pushUpdateServiceTest(){
		// Creating the user list to push
		List<UserDTO> itemList = new ArrayList<UserDTO>();
		UserDTO user = createUserDTO(10L, "decimousuarioactualizado@splitemapp.com", "decimo usuario actualizado");
		itemList.add(user);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USERS_PATH;

		// Crafting the request object
		PushUserRequest request = new PushUserRequest();
		request.setLastPushSuccessAt(TimeUtils.getUTCDate());
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}

	private UserDTO createUserDTO(Long id, String userName, String fullName){
		// Creating user DTO object.
		UserDTO user = new UserDTO();
		user.setId(id);
		user.setUsername(userName);
		user.setFullName(fullName);
		user.setLastLogin(null);
		user.setLoginCnt(1);
		user.setPassword("12345");
		user.setCreatedIpAddress("127.0.0.1");
		user.setUpdatedIpAddress("127.0.0.1");
		user.setUserStatusId((short)1);
		user.setCreatedAt(new Date(10000));
		user.setUpdatedAt(new Date(10000));
		return user;
	}
}
