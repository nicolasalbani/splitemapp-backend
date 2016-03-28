package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.UserExpenseDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.request.PushUserExpenseRequest;
import com.splitemapp.commons.domain.dto.response.PullUserExpenseResponse;
import com.splitemapp.commons.domain.dto.response.PushLongResponse;
import com.splitemapp.commons.rest.RestUtils;
import com.splitemapp.commons.utils.TimeUtils;

public class UserExpenseServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PULL_USER_EXPENSES_PATH);
		servicePathList.add(ServiceConstants.PUSH_USER_EXPENSES_PATH);
		return servicePathList;
	}

	@Test
	public void pullServiceTest(){
		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PULL_USER_EXPENSES_PATH;

		// Crafting the request object
		PullRequest request = new PullRequest();
		request.setLastPullSuccessAt(new Date(100));
		request.setToken(TOKEN);

		// Making rest service call
		PullUserExpenseResponse response = RestUtils.callRestService(serviceUrl, request, PullUserExpenseResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());

		// Validating that users linked by projects are also retrieved
		boolean hasLinkedUserExpenses = false;
		Iterator<UserExpenseDTO> iterator = response.getItemSet().iterator();
		while(iterator.hasNext()){
			UserExpenseDTO userExpenseDTO = iterator.next();
			if(userExpenseDTO.getUserId().equals(2L) && userExpenseDTO.getNote().equals("Gaseosas")){
				hasLinkedUserExpenses = true;
			} else if(userExpenseDTO.getId().equals(3L)){
				Assert.fail("Should not be obtaining an expense from a user from an unlinked project");
			} else if(userExpenseDTO.getId().equals(20L)){
				Assert.fail("Should not be obtaining an expense from an unlinked project");
			}
		}
		assertTrue(hasLinkedUserExpenses);
	}

	@Test
	public void pushCreateServiceTest(){
		// Creating the user list to push
		List<UserExpenseDTO> itemList = new ArrayList<UserExpenseDTO>();
		UserExpenseDTO user = createUserExpenseDTO(10L,1L,1L,"Varios", 240);
		itemList.add(user);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USER_EXPENSES_PATH;

		// Crafting the request object
		PushUserExpenseRequest request = new PushUserExpenseRequest();
		request.setLastPushSuccessAt(new Date(10000));
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
		List<UserExpenseDTO> itemList = new ArrayList<UserExpenseDTO>();
		UserExpenseDTO user = createUserExpenseDTO(3L,3L,3L,"Carne Actualizada", 240);
		itemList.add(user);

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.PUSH_USER_EXPENSES_PATH;

		// Crafting the request object
		PushUserExpenseRequest request = new PushUserExpenseRequest();
		request.setLastPushSuccessAt(TimeUtils.getDateUTC());
		request.setToken(TOKEN);
		request.setItemList(itemList);

		// Making rest service call
		PushLongResponse response = RestUtils.callRestService(serviceUrl, request, PushLongResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());
	}

	private UserExpenseDTO createUserExpenseDTO(Long id, Long projectId, Long userId, String note, int expense){
		// Creating user expense DTO object.
		UserExpenseDTO userExpense = new UserExpenseDTO();
		userExpense.setId(id);
		userExpense.setUser(userId);
		userExpense.setProjectId(projectId);
		userExpense.setExpense(new BigDecimal(expense));
		userExpense.setExpenseCategoryDTO((short)1);
		userExpense.setNote(note);
		userExpense.setExpenseDate(new Date(10000));
		userExpense.setCreatedAt(new Date(10000));
		userExpense.setUpdatedAt(new Date(10000));
		userExpense.setUpdatedBy(userId);
		userExpense.setPushedBy(userId);
		return userExpense;
	}
}
