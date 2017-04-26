package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.ExpenseStatus;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class ExpenseStatusEndpoint extends DomainDAO<ExpenseStatus, Short> {

	@Override
	public Class<ExpenseStatus> getEntityClass() {
		return ExpenseStatus.class;
	}

}
