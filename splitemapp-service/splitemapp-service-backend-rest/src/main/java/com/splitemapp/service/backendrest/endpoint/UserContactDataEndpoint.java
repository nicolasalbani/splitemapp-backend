package com.splitemapp.service.backendrest.endpoint;

import com.fairpay.domainmodel.domain.User;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserContactDataEndpoint extends DomainDAO<User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
