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
		return "SELECT DISTINCT UCD FROM user_contact_data UCD LEFT JOIN UCD.user.userToProjects " +LINKED_BY_PROJECT_SQL+ " AND UTP.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT UCD FROM user_contact_data UCD LEFT JOIN UCD.user.userToProjects " +LINKED_BY_PROJECT_SQL+ " AND UTP.updatedAt > :" +UPDATED_AT_PARAMETER;
	}
	
	@Override
	public String getPushedAfterQuery() {
		return "SELECT DISTINCT UCD FROM user_contact_data UCD LEFT JOIN UCD.user.userToProjects " +LINKED_BY_PROJECT_SQL+ " AND UTP.pushedAt > :" +PUSHED_AT_PARAMETER;
	}

}
