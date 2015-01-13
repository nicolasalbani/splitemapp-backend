package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserToGroup;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserToGroupEndpoint extends UpdateableDomainDAO<UserToGroup,Long> {

	@Override
	public Class<UserToGroup> getEntityClass() {
		return UserToGroup.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "FROM user_to_group UTG WHERE UTG.user.id  = :" +USER_ID_PARAMETER  + " AND UTG.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "FROM user_to_group UTG WHERE UTG.user.id  = :" +USER_ID_PARAMETER  + " AND UTG.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}
