package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserContactDataEndpoint extends UpdateableDomainDAO<UserContactData,Long> {

	@Override
	public Class<UserContactData> getEntityClass() {
		return UserContactData.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT UCD FROM user_contact_data UCD WHERE UCD.user.id = :" +USER_ID_PARAMETER+ " AND UCD.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT UCD FROM user_contact_data UCD WHERE UCD.user.id = :" +USER_ID_PARAMETER+ " AND UCD.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}