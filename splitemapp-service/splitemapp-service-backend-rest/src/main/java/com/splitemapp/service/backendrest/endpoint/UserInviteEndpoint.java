package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.service.domainmodel.domain.UserInvite;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserInviteEndpoint extends DomainDAO<UserInvite> {

	@Override
	public Class<UserInvite> getEntityClass() {
		return UserInvite.class;
	}

}
