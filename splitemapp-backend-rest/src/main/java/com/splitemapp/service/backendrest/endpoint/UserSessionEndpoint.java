package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserSessionEndpoint extends DomainDAO<UserSession> {

	@Override
	public Class<UserSession> getEntityClass() {
		return UserSession.class;
	}

}
