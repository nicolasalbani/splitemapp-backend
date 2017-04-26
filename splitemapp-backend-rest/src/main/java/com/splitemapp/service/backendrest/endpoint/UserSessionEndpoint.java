package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserSessionEndpoint extends UpdateableDomainDAO<UserSession,Long> {

	@Override
	public Class<UserSession> getEntityClass() {
		return UserSession.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT US FROM user_session US LEFT JOIN US.user.userToProjects " +LINKED_BY_PROJECT_SQL;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT US FROM user_session US LEFT JOIN US.user.userToProjects " +LINKED_BY_PROJECT_SQL;
	}
	
	@Override
	public String getPushedAfterByUserQuery() {
		return "SELECT DISTINCT US FROM userSession US LEFT JOIN US.user.userToProjects " +LINKED_BY_PROJECT_SQL;
	}

	@Override
	public String getPushedAfterByProjectQuery() {
		return "SELECT DISTINCT US FROM user_session US LEFT JOIN US.user.userToProjects " +LINKED_BY_SINGLE_PROJECT_SQL;
	}

}
