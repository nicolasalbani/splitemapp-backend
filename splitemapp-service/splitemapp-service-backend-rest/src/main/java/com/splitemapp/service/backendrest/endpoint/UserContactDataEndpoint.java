package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserContactDataEndpoint extends UpdateableDomainDAO<UserContactData> {

	@Override
	public Class<UserContactData> getEntityClass() {
		return UserContactData.class;
	}

	@Override
	public String getFilterByUserIdQuery() {
		return "SELECT id FROM user_contact_data U WHERE U.user = :" +USER_ID_PARAMETER;
	}

}
