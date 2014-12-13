package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.Group;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class GroupEndpoint extends UpdateableDomainDAO<Group> {

	@Override
	public Class<Group> getEntityClass() {
		return Group.class;
	}

	@Override
	public String getFilterByUserIdQuery() {
		return "SELECT group_id FROM user_to_group U WHERE U.user = :" +USER_ID_PARAMETER;
	}

}
