package com.splitemapp.service.backendrest.endpoint;

import com.fairpay.domainmodel.domain.ExpenseCategory;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class ExpenseCategoryEndpoint extends DomainDAO<ExpenseCategory> {

	@Override
	public Class<ExpenseCategory> getEntityClass() {
		return ExpenseCategory.class;
	}

}