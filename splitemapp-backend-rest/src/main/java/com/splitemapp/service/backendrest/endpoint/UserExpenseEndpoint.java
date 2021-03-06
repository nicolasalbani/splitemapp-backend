package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserExpenseEndpoint extends UpdateableDomainDAO<UserExpense,Long> {

	@Override
	public Class<UserExpense> getEntityClass() {
		return UserExpense.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT UE FROM user_expense UE LEFT JOIN UE.project.userToProjects  " +LINKED_BY_PROJECT_SQL+ " AND UE.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT UE FROM user_expense UE LEFT JOIN UE.project.userToProjects  " +LINKED_BY_PROJECT_SQL+ " AND UE.updatedAt > :" +UPDATED_AT_PARAMETER;
	}
	
	@Override
	public String getPushedAfterByUserQuery() {
		return "SELECT DISTINCT UE FROM user_expense UE LEFT JOIN UE.project.userToProjects  " +LINKED_BY_PROJECT_SQL+ " AND UE.pushedAt > :" +PUSHED_AT_PARAMETER;
	}

	@Override
	public String getPushedAfterByProjectQuery() {
		return "SELECT DISTINCT UE FROM user_expense UE LEFT JOIN UE.project.userToProjects  " +LINKED_BY_SINGLE_PROJECT_SQL+ " AND UE.pushedAt > :" +PUSHED_AT_PARAMETER;
	}

}
