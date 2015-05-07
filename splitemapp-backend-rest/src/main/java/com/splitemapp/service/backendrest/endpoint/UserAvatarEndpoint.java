package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.UserAvatar;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserAvatarEndpoint extends UpdateableDomainDAO<UserAvatar,Long> {

	@Override
	public Class<UserAvatar> getEntityClass() {
		return UserAvatar.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT UA FROM user_avatar UA WHERE UA.userId = :" +USER_ID_PARAMETER + " AND UA.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT UA FROM user_avatar UA WHERE UA.userId = :" +USER_ID_PARAMETER + " AND UA.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}
