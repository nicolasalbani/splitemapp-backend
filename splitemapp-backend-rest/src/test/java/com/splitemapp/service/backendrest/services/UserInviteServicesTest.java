package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.UserInviteDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.request.PushUserInviteRequest;
import com.splitemapp.commons.domain.dto.response.PullUserInviteResponse;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;

public class UserInviteServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PULL_USER_INVITES_PATH);
		servicePathList.add(ServiceConstants.PUSH_USER_INVITES_PATH);
		return servicePathList;
	}

	@Test
	public void pullServiceTest(){
		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PULL_USER_INVITES_PATH;

		// Crafting the request object
		PullRequest request = new PullRequest();
		request.setLastPullSuccessAt(new Date(100));
		request.setToken(TOKEN);

		// Making rest service call
		PullUserInviteResponse response = RestUtils.callRestService(serviceUrl, request, PullUserInviteResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());

		// Validating that users linked by projects are also retrieved
		boolean firstUserInviteFound = false;
		boolean secondUserInviteFound = false;
		Iterator<UserInviteDTO> iterator = response.getItemSet().iterator();
		while(iterator.hasNext()){
			UserInviteDTO userInviteDTO = iterator.next();
			if(userInviteDTO.getProjectId().equals(1L) && userInviteDTO.getUserId().equals(1L) && userInviteDTO.getEmail().equals("juanperez@splitemapp.com")){
				firstUserInviteFound = true;
			} else if(userInviteDTO.getProjectId().equals(2L) && userInviteDTO.getUserId().equals(1L) && userInviteDTO.getEmail().equals("juanperez@splitemapp.com")){
				secondUserInviteFound = true;
			}
		}
		assertTrue(firstUserInviteFound && secondUserInviteFound);
	}

	@Test
	public void pushServiceTest(){
		// Creating the user list to push
		List<UserInviteDTO> itemList = new ArrayList<UserInviteDTO>();
		UserInviteDTO userToProject = createUserInviteDTO();
		itemList.add(userToProject);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USER_INVITES_PATH;

		// Crafting the request object
		PushUserInviteRequest request = new PushUserInviteRequest();
		request.setLastPushSuccessAt(new Date(100));
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}

	private UserInviteDTO createUserInviteDTO(){
		// Creating user DTO object. We always use the same ID because it will be updated on the server side
		UserInviteDTO userInvite = new UserInviteDTO();
		userInvite.setCreatedAt(new Date(20000));
		userInvite.setEmail("juanperez@splitemapp.com");
		userInvite.setInviteStatusId((short)1);
		userInvite.setProjectId(3L);
		userInvite.setUpdatedAt(new Date(20000));
		userInvite.setUserId(1L);
		return userInvite;
	}
}
