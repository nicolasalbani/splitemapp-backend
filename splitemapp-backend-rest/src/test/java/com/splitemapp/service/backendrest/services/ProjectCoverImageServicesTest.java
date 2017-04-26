package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.ProjectCoverImageDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.request.PushProjectCoverImageRequest;
import com.splitemapp.commons.domain.dto.response.PullProjectCoverImageResponse;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;
import com.splitemapp.commons.utils.TimeUtils;

public class ProjectCoverImageServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PULL_PROJECT_COVER_IMAGES_PATH);
		servicePathList.add(ServiceConstants.PUSH_PROJECT_COVER_IMAGES_PATH);
		return servicePathList;
	}

	@Test
	public void pullServiceTest(){
		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PULL_PROJECT_COVER_IMAGES_PATH;

		// Crafting the request object
		PullRequest request = new PullRequest();
		request.setLastPullSuccessAt(new Date(100));
		request.setToken(TOKEN);

		// Making rest service call
		PullProjectCoverImageResponse response = RestUtils.callRestService(serviceUrl, request, PullProjectCoverImageResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());

		// Validating that project cover images linked by projects are also retrieved
		boolean hasLinkedProjectCoverImage = false;
		Iterator<ProjectCoverImageDTO> iterator = response.getItemSet().iterator();
		while(iterator.hasNext()){
			ProjectCoverImageDTO projectCoverImageDTO = iterator.next();
			if(projectCoverImageDTO.getProjectId().equals(1L)){
				hasLinkedProjectCoverImage = true;
			} else if(projectCoverImageDTO.getProjectId().equals(3L)){
				Assert.fail("Should not be obtaining a project cover image from an unlinked project");
			}
		}
		assertTrue(hasLinkedProjectCoverImage);
	}

	@Test
	public void pushCreateServiceTest(){
		// Creating the user list to push
		List<ProjectCoverImageDTO> itemList = new ArrayList<ProjectCoverImageDTO>();
		ProjectCoverImageDTO projectCoverImageDTO = createProjectCoverImageDTO(3l, 10l, 1l);
		itemList.add(projectCoverImageDTO);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_PROJECT_COVER_IMAGES_PATH;

		// Crafting the request object
		PushProjectCoverImageRequest request = new PushProjectCoverImageRequest();
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
		List<ProjectCoverImageDTO> itemList = new ArrayList<ProjectCoverImageDTO>();
		ProjectCoverImageDTO projectCoverImageDTO = createProjectCoverImageDTO(2l, 3l, 1l);
		itemList.add(projectCoverImageDTO);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_PROJECT_COVER_IMAGES_PATH;

		// Crafting the request object
		PushProjectCoverImageRequest request = new PushProjectCoverImageRequest();
		request.setLastPushSuccessAt(TimeUtils.getUTCDate());
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}

	private ProjectCoverImageDTO createProjectCoverImageDTO(Long id, Long projectId, Long userId){
		// Creating project cover image DTO object
		ProjectCoverImageDTO projectCoverImageDTO = new ProjectCoverImageDTO();
		projectCoverImageDTO.setId(id);
		projectCoverImageDTO.setProjectId(projectId);
		projectCoverImageDTO.setAvatarData(new byte[1]);
		projectCoverImageDTO.setCreatedAt(new Date(1000));
		projectCoverImageDTO.setUpdatedAt(new Date(1000));
		projectCoverImageDTO.setUpdatedBy(userId);
		projectCoverImageDTO.setPushedBy(userId);
		return projectCoverImageDTO;
	}
}
