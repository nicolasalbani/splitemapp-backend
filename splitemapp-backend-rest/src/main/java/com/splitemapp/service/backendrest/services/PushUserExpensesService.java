package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.ExpenseCategory;
import com.splitemapp.commons.domain.Project;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserExpenseDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.endpoint.ExpenseCategoryEndpoint;
import com.splitemapp.service.backendrest.endpoint.ProjectEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserExpenseEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PUSH_USER_EXPENSES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushUserExpensesService {

	UserSessionEndpoint userSessionEndpoint;
	UserEndpoint userEndpoint;
	ProjectEndpoint projectEndpoint;
	ExpenseCategoryEndpoint expenseCategoryEndpoint;
	UserExpenseEndpoint userExpenseEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PushResponse<Long> printMessage(PushRequest<UserExpenseDTO> request) throws ParseException {
		// We create a pull groups response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();
		response.setSuccess(false);

		// Creating the pushedAt date
		Date pushedAt = new Date();

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(UserExpenseDTO userExpenseDTO:request.getItemList()){
				// We create the user expense object
				User user = userEndpoint.findById(userExpenseDTO.getUserId());
				Project project = projectEndpoint.findById(userExpenseDTO.getProjectId());
				ExpenseCategory expenseCategory = expenseCategoryEndpoint.findById(userExpenseDTO.getExpenseCategoryId());
				UserExpense userExpense = new UserExpense(user, project, expenseCategory, userExpenseDTO);
				
				// We update the pushedAt date
				userExpense.setPushedAt(pushedAt);

				if(Utils.isDateAfter(userExpenseDTO.getCreatedAt(),request.getLastPushSuccessAt())){
					// We persist the entry to the database
					userExpense.setId(null);
					userExpenseEndpoint.persist(userExpense);

					// We add the IdUpdate element to the response list
					response.getIdUpdateList().add(new IdUpdate<Long>(userExpenseDTO.getId(), userExpense.getId()));
				} else {
					// We merge the entry to the database
					userExpenseEndpoint.merge(userExpense);
				}
			}

			// We set the success flag and pushedAt 
			response.setPushedAt(pushedAt);
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

	public UserEndpoint getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(UserEndpoint userEndpoint) {
		this.userEndpoint = userEndpoint;
	}

	public ProjectEndpoint getProjectEndpoint() {
		return projectEndpoint;
	}

	public void setProjectEndpoint(ProjectEndpoint projectEndpoint) {
		this.projectEndpoint = projectEndpoint;
	}

	public ExpenseCategoryEndpoint getExpenseCategoryEndpoint() {
		return expenseCategoryEndpoint;
	}

	public void setExpenseCategoryEndpoint(ExpenseCategoryEndpoint expenseCategoryEndpoint) {
		this.expenseCategoryEndpoint = expenseCategoryEndpoint;
	}

	public UserExpenseEndpoint getUserExpenseEndpoint() {
		return userExpenseEndpoint;
	}

	public void setUserExpenseEndpoint(UserExpenseEndpoint userExpenseEndpoint) {
		this.userExpenseEndpoint = userExpenseEndpoint;
	}

}