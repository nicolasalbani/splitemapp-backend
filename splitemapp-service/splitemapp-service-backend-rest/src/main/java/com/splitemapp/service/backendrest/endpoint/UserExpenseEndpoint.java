package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserExpenseEndpoint extends DomainDAO<UserExpense> {

	@Override
	public Class<UserExpense> getEntityClass() {
		return UserExpense.class;
	}

}
