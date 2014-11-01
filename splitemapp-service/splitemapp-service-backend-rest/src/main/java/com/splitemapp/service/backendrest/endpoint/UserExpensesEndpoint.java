package com.splitemapp.service.backendrest.endpoint;

import com.fairpay.domainmodel.domain.UserExpenses;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserExpensesEndpoint extends DomainDAO<UserExpenses> {

	@Override
	public Class<UserExpenses> getEntityClass() {
		return UserExpenses.class;
	}

}
