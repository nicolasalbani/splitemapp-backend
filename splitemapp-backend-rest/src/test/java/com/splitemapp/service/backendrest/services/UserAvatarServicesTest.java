package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.UserAvatarDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.request.PushUserAvatarRequest;
import com.splitemapp.commons.domain.dto.response.PullUserAvatarResponse;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;

public class UserAvatarServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PULL_USER_AVATARS_PATH);
		servicePathList.add(ServiceConstants.PUSH_USER_AVATARS_PATH);
		return servicePathList;
	}

	@Test
	public void pullServiceTest(){
		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PULL_USER_AVATARS_PATH;

		// Crafting the request object
		PullRequest request = new PullRequest();
		request.setLastPullSuccessAt(new Date(100));
		request.setToken(TOKEN);

		// Making rest service call
		PullUserAvatarResponse response = RestUtils.callRestService(serviceUrl, request, PullUserAvatarResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());

		// Validating that users linked by projects are also retrieved
		boolean hasLinkedUsers = false;
		Iterator<UserAvatarDTO> iterator = response.getItemSet().iterator();
		while(iterator.hasNext()){
			UserAvatarDTO userAvatarDTO = iterator.next();
			if(userAvatarDTO.getUserId().equals(2L)){
				hasLinkedUsers = true;
			} else if(userAvatarDTO.getUserId().equals(3L)){
				Assert.fail("Should not be obtaining a user from an unlinked project");
			}
		}
		assertTrue(hasLinkedUsers);
	}

	@Test
	public void pushServiceCreateTest(){
		// Creating the user list to push
		List<UserAvatarDTO> itemList = new ArrayList<UserAvatarDTO>();
		UserAvatarDTO userAvatar = createUserAvatarDTO(3L,3L);
		itemList.add(userAvatar);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USER_AVATARS_PATH;

		// Crafting the request object
		PushUserAvatarRequest request = new PushUserAvatarRequest();
		request.setLastPushSuccessAt(new Date(100));
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}
	
	@Test
	public void pushServiceUpdateTest(){
		// Creating the user list to push
		List<UserAvatarDTO> itemList = new ArrayList<UserAvatarDTO>();
		UserAvatarDTO userAvatar = createUserAvatarDTO(10L,10L);
		itemList.add(userAvatar);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USER_AVATARS_PATH;

		// Crafting the request object
		PushUserAvatarRequest request = new PushUserAvatarRequest();
		request.setLastPushSuccessAt(new Date());
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}

	private UserAvatarDTO createUserAvatarDTO(Long id, Long userId){
		// Creating user avatar DTO object.
		UserAvatarDTO user = new UserAvatarDTO();
		user.setId(id);
		user.setUserId(userId);
		user.setAvatarData(new byte[1]);
		user.setCreatedAt(new Date(10000));
		user.setUpdatedAt(new Date(10000));
		return user;
	}
}
