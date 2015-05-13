package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserToProjectStatus;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserToProjectStatusEndpoint extends DomainDAO<UserToProjectStatus, Short> {

	@Override
	public Class<UserToProjectStatus> getEntityClass() {
		return UserToProjectStatus.class;
	}

}
