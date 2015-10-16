package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.User;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserEndpoint extends UpdateableDomainDAO<User,Long> {
	

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT U FROM user U LEFT JOIN U.userToProjects UTP WHERE UTP.user.id = :" +USER_ID_PARAMETER + " AND U.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT U FROM user U LEFT JOIN U.userToProjects UTP WHERE UTP.user.id = :" +USER_ID_PARAMETER + " AND U.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}
