package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserExpenseEndpoint extends UpdateableDomainDAO<UserExpense> {

	@Override
	public Class<UserExpense> getEntityClass() {
		return UserExpense.class;
	}

	@Override
	public String getFilterByUserIdQuery() {
		return "SELECT id FROM user_expense U WHERE U.project in (SELECT project_id FROM user_to_project P WHERE P.user = :" +USER_ID_PARAMETER +")";
	}

}
