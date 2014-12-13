package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserToGroup;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserToGroupEndpoint extends UpdateableDomainDAO<UserToGroup> {

	@Override
	public Class<UserToGroup> getEntityClass() {
		return UserToGroup.class;
	}

	@Override
	public String getFilterByUserIdQuery() {
		return "SELECT id FROM user_to_group U WHERE U.group in (SELECT group_id FROM user_to_group P WHERE P.user = :" +USER_ID_PARAMETER +")";
	}

}
