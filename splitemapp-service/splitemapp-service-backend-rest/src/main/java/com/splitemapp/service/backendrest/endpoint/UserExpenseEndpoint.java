package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.domainmodel.domain.UserExpenses;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserExpensesEndpoint extends DomainDAO<UserExpenses> {

	@Override
	public Class<UserExpenses> getEntityClass() {
		return UserExpenses.class;
	}

}
