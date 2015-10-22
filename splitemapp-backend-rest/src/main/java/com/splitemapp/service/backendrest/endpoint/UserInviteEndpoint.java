package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserInvite;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserInviteEndpoint extends UpdateableDomainDAO<UserInvite,Long> {

	@Override
	public Class<UserInvite> getEntityClass() {
		return UserInvite.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT UI FROM user_invite UI WHERE UI.user.id = :" +USER_ID_PARAMETER + " AND UI.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT UI FROM user_invite UI WHERE UI.user.id = :" +USER_ID_PARAMETER + " AND UI.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}
