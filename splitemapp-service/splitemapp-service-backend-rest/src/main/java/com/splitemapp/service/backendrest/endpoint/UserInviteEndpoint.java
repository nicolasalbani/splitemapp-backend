package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserInvite;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserInviteEndpoint extends UpdateableDomainDAO<UserInvite> {

	@Override
	public Class<UserInvite> getEntityClass() {
		return UserInvite.class;
	}

	@Override
	public String getFilterByUserIdQuery() {
		return "SELECT id FROM user_invite U WHERE U.project in (SELECT project_id FROM user_to_project P WHERE P.user = :" +USER_ID_PARAMETER +")";
	}

}
