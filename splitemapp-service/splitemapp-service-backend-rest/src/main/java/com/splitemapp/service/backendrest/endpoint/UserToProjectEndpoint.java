package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserToProject;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserToProjectEndpoint extends UpdateableDomainDAO<UserToProject> {

	@Override
	public Class<UserToProject> getEntityClass() {
		return UserToProject.class;
	}

	@Override
	public String getFilterByUserIdQuery() {
		return "SELECT id FROM user_to_project U WHERE U.project in (SELECT project_id FROM user_to_project P WHERE P.user = :" +USER_ID_PARAMETER +")";
	}

}
