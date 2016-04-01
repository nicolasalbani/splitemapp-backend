package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserExpenseDTO;
import com.splitemapp.commons.domain.dto.request.PullRequest;
import com.splitemapp.commons.domain.dto.response.PullResponse;
import com.splitemapp.commons.utils.TimeUtils;
import com.splitemapp.service.backendrest.endpoint.UserExpenseEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PULL_USER_EXPENSES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PullUserExpensesService {

	UserSessionEndpoint userSessionEndpoint;
	UserExpenseEndpoint userExpenseEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PullResponse<UserExpenseDTO> printMessage(PullRequest request) throws ParseException {
		// We create a pull all sync response object setting success to false by default
		PullResponse<UserExpenseDTO> response = new PullResponse<UserExpenseDTO>();

		// Creating the pulledAt date
		Date pulledAt = TimeUtils.getUTCDate();

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			User user = userSession.getUser();
			
			// Getting the proper list based on parameters
			List<UserExpense> userExpenses = null;
			if(request.isPullAllDates()){
				userExpenses = userExpenseEndpoint.findPushedAfterByProject(new Date(0), request.getProjectId());
			} else {
				userExpenses = userExpenseEndpoint.findPushedAfterByUser(request.getLastPullSuccessAt(), user.getId());
			}

			// We set the user expense set
			Set<UserExpenseDTO> userExpenseDTOs = new HashSet<UserExpenseDTO>();
			for(UserExpense userExpense:userExpenses){
				userExpenseDTOs.add(new UserExpenseDTO(userExpense));
			}
			
			// Setting the user expense set
			response.setItemSet(userExpenseDTOs);

			// We set the success flag and pulledAt
			response.setPulledAt(pulledAt);
			response.setSuccess(true);
		}

		return response;
	}


	// Getters and setters

	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}

	public UserExpenseEndpoint getUserExpenseEndpoint() {
		return userExpenseEndpoint;
	}

	public void setUserExpenseEndpoint(UserExpenseEndpoint userExpenseEndpoint) {
		this.userExpenseEndpoint = userExpenseEndpoint;
	}

}