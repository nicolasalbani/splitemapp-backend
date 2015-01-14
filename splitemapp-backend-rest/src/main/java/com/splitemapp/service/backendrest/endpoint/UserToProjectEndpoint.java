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
		return "SELECT DISTINCT FROM user_to_project UTP WHERE UTP.user.id  = :" +USER_ID_PARAMETER  + " AND UTP.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT FROM user_to_project UTP WHERE UTP.user.id  = :" +USER_ID_PARAMETER  + " AND UTP.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}
