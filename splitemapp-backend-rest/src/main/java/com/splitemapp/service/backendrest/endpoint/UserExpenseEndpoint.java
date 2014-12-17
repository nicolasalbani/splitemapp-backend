package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserExpenseEndpoint extends UpdateableDomainDAO<UserExpense> {

	@Override
	public Class<UserExpense> getEntityClass() {
		return UserExpense.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT UE FROM user_expense UE, user_to_project UTP WHERE UE.project.id = UTP.project.id AND UTP.user.id = :" +USER_ID_PARAMETER + " AND UE.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT UE FROM user_expense UE, user_to_project UTP WHERE UE.project.id = UTP.project.id AND UTP.user.id = :" +USER_ID_PARAMETER + " AND UE.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}
