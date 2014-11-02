package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.service.domainmodel.domain.User;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserEndpoint extends DomainDAO<User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
