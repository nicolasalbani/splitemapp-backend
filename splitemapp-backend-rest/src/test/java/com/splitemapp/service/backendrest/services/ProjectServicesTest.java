package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.ProjectDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.request.PushProjectRequest;
import com.splitemapp.commons.domain.dto.response.PullProjectResponse;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;

public class ProjectServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PULL_PROJECTS_PATH);
		servicePathList.add(ServiceConstants.PUSH_PROJECTS_PATH);
		return servicePathList;
	}

	@Test
	public void pullServiceTest(){
		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PULL_PROJECTS_PATH;

		// Crafting the request object
		PullRequest request = new PullRequest();
		request.setLastPullSuccessAt(new Date(100));
		request.setToken(TOKEN);

		// Making rest service call
		PullProjectResponse response = RestUtils.callRestService(serviceUrl, request, PullProjectResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());

		// Validating that linked projects are also retrieved
		boolean userAssociatedWithProject = false;
		Iterator<ProjectDTO> iterator = response.getItemSet().iterator();
		while(iterator.hasNext()){
			ProjectDTO projectDTO = iterator.next();
			if(projectDTO.getTitle().equals("Segundo Proyecto")){
				userAssociatedWithProject = true;
			} else if(projectDTO.getTitle().equals("Tercer Proyecto")){
				fail("Should not get project which is not associated with user");
			}
		}
		assertTrue(userAssociatedWithProject);
	}

	@Test
	public void pushCreateServiceTest(){
		// Creating the user list to push
		List<ProjectDTO> itemList = new ArrayList<ProjectDTO>();
		ProjectDTO project = createProjectDTO(4L, "Cuarto Proyecto", 1L);
		itemList.add(project);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_PROJECTS_PATH;

		// Crafting the request object
		PushProjectRequest request = new PushProjectRequest();
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
		List<ProjectDTO> itemList = new ArrayList<ProjectDTO>();
		ProjectDTO project = createProjectDTO(10L, "Decimo Proyecto Actualizado", 1L);
		itemList.add(project);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_PROJECTS_PATH;

		// Crafting the request object
		PushProjectRequest request = new PushProjectRequest();
		request.setLastPushSuccessAt(new Date());
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}

	private ProjectDTO createProjectDTO(Long id, String title, Long userId){
		// Creating user DTO object.
		ProjectDTO project = new ProjectDTO();
		project.setBudget(new BigDecimal(100));
		project.setProjectStatusId((short)1);
		project.setProjectTypeId((short)1);
		project.setId(id);
		project.setTitle(title);
		project.setCreatedAt(new Date(10000));
		project.setUpdatedAt(new Date(10000));
		project.setUpdatedBy(userId);
		project.setPushedBy(userId);
		return project;
	}
}
