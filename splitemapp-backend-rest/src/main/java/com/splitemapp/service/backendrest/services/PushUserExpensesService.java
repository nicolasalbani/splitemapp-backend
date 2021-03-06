package com.splitemapp.service.backendrest.services;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.Action;
import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.ExpenseCategory;
import com.splitemapp.commons.domain.ExpenseStatus;
import com.splitemapp.commons.domain.Project;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.UserExpenseDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.utils.TimeUtils;
import com.splitemapp.service.backendrest.endpoint.ExpenseCategoryEndpoint;
import com.splitemapp.service.backendrest.endpoint.ExpenseStatusEndpoint;
import com.splitemapp.service.backendrest.endpoint.ProjectEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserExpenseEndpoint;

@Service
@Path(ServiceConstants.PUSH_USER_EXPENSES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushUserExpensesService extends PushNotificationService{

	private static Logger logger = Logger.getLogger(PushUserExpensesService.class);

	UserEndpoint userEndpoint;
	ProjectEndpoint projectEndpoint;
	ExpenseCategoryEndpoint expenseCategoryEndpoint;
	ExpenseStatusEndpoint expenseStatusEndpoint;
	UserExpenseEndpoint userExpenseEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}

	@POST
	public PushResponse<Long> printMessage(PushRequest<UserExpenseDTO> request) throws ParseException {
		// Service start time
		DateTime serviceStartTime = new DateTime();

		// We create a pull groups response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();

		// Creating the pushedAt date
		Date pushedAt = TimeUtils.getUTCDate();

		// Defining action and details to be notified
		String action = "";

		UserSession userSession = getUserSession(request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(UserExpenseDTO userExpenseDTO:request.getItemList()){
				// We create the user expense object
				User user = userEndpoint.findById(userExpenseDTO.getUserId());
				Project project = projectEndpoint.findById(userExpenseDTO.getProjectId());
				ExpenseCategory expenseCategory = expenseCategoryEndpoint.findById(userExpenseDTO.getExpenseCategoryId());
				ExpenseStatus expenseStatus = expenseStatusEndpoint.findById(userExpenseDTO.getExpenseStatusId());
				User updatedBy = userEndpoint.findById(userExpenseDTO.getUpdatedBy());
				User pushedBy = userEndpoint.findById(userExpenseDTO.getPushedBy());
				UserExpense userExpense = new UserExpense(user, project, expenseCategory, expenseStatus, updatedBy, pushedBy, userExpenseDTO);

				// We update the pushedAt date
				userExpense.setPushedAt(pushedAt);

				if(userExpenseDTO.getPushedAt() == null){
					// Setting the action
					action = Action.ADD_USER_EXPENSE;

					// We persist the entry to the database
					userExpense.setId(null);
					userExpenseEndpoint.persist(userExpense);

					// We add the IdUpdate element to the response list
					response.getIdUpdateList().add(new IdUpdate<Long>(userExpenseDTO.getId(), userExpense.getId()));
				} else {
					// Setting the action
					action = Action.UPDATE_USER_EXPENSE;

					// We merge the entry to the database
					userExpenseEndpoint.merge(userExpense);
				}

				// Sending GCM notification to all related clients
				sendGcmNotification(userSession, action, userExpense.getProject().getTitle(), userExpense.getProject().getId());
			}

			// We set the success flag and pushedAt 
			response.setPushedAt(pushedAt);
			response.setSuccess(true);
		}

		// Calculating service time
		logger.info(getClass().getSimpleName() +" time was: "+ (new DateTime().getMillis()-serviceStartTime.getMillis()+ "ms"));

		return response;
	}


	// Getters and setters

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

	public ExpenseStatusEndpoint getExpenseStatusEndpoint() {
		return expenseStatusEndpoint;
	}

	public void setExpenseStatusEndpoint(ExpenseStatusEndpoint expenseStatusEndpoint) {
		this.expenseStatusEndpoint = expenseStatusEndpoint;
	}

}