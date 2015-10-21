package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.UserToProjectDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.request.PushUserToProjectRequest;
import com.splitemapp.commons.domain.dto.response.PullUserToProjectResponse;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;

public class UserToProjectServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PULL_USER_TO_PROJECTS_PATH);
		servicePathList.add(ServiceConstants.PUSH_USER_TO_PROJECTS_PATH);
		return servicePathList;
	}

	@Test
	public void pullServiceTest(){
		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PULL_USER_TO_PROJECTS_PATH;

		// Crafting the request object
		PullRequest request = new PullRequest();
		request.setLastPullSuccessAt(new Date(100));
		request.setToken(TOKEN);

		// Making rest service call
		PullUserToProjectResponse response = RestUtils.callRestService(serviceUrl, request, PullUserToProjectResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());

		// Validating that users linked by projects are also retrieved
		boolean userAssociatedWithProject = false;
		Iterator<UserToProjectDTO> iterator = response.getItemSet().iterator();
		while(iterator.hasNext()){
			UserToProjectDTO userToProjectDTO = iterator.next();
			if(userToProjectDTO.getProjectId().equals(1L) && userToProjectDTO.getUserId().equals(1L)){
				userAssociatedWithProject = true;
			}
		}
		assertTrue(userAssociatedWithProject);
	}

	@Test
	public void pushServiceTest(){
		// Creating the user list to push
		List<UserToProjectDTO> itemList = new ArrayList<UserToProjectDTO>();
		UserToProjectDTO userToProject = createUserToProjectDTO(3L,1L);
		itemList.add(userToProject);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USER_TO_PROJECTS_PATH;

		// Crafting the request object
		PushUserToProjectRequest request = new PushUserToProjectRequest();
		request.setLastPushSuccessAt(new Date(100));
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}

	private UserToProjectDTO createUserToProjectDTO(Long projectId, Long userId){
		// Creating user DTO object. We always use the same ID because it will be updated on the server side
		UserToProjectDTO userToProject = new UserToProjectDTO();
		userToProject.setCreatedAt(new Date(10000));
		userToProject.setExpensesShare(new BigDecimal(100));
		userToProject.setProjectDTO(projectId);
		userToProject.setUpdatedAt(new Date(10000));
		userToProject.setUserId(userId);
		userToProject.setUserToProjectStatusId((short)1);
		return userToProject;
	}
}
