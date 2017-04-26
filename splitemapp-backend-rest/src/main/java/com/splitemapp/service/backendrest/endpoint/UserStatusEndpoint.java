package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserStatus;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserStatusEndpoint extends DomainDAO<UserStatus,Short> {

	@Override
	public Class<UserStatus> getEntityClass() {
		return UserStatus.class;
	}

}
