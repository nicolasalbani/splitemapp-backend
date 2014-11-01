package com.splitemapp.service.backendrest.endpoint;

import com.fairpay.domainmodel.domain.UserToGroup;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserToProjectEndpoint extends DomainDAO<UserToGroup> {

	@Override
	public Class<UserToGroup> getEntityClass() {
		return UserToGroup.class;
	}

}
