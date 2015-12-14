package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserToProject;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserToProjectEndpoint extends UpdateableDomainDAO<UserToProject,Long> {

	@Override
	public Class<UserToProject> getEntityClass() {
		return UserToProject.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT UTP FROM user_to_project " +LINKED_BY_PROJECT_SQL+ " AND UTP.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT UTP FROM user_to_project " +LINKED_BY_PROJECT_SQL+ " AND UTP.updatedAt > :" +UPDATED_AT_PARAMETER;
	}
	
	@Override
	public String getPushedAfterQuery() {
		return "SELECT DISTINCT UTP FROM user_to_project " +LINKED_BY_PROJECT_SQL+ " AND UTP.pushedAt > :" +PUSHED_AT_PARAMETER;
	}

}
