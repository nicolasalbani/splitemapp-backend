package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserContactDataEndpoint extends DomainDAO<UserContactData> {

	@Override
	public Class<UserContactData> getEntityClass() {
		return UserContactData.class;
	}

}
