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
		return "SELECT DISTINCT UA FROM user_avatar UA LEFT JOIN UA.user.userToProjects " +LINKED_BY_PROJECT_SQL+ " AND UTP.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT UA FROM user_avatar UA LEFT JOIN UA.user.userToProjects " +LINKED_BY_PROJECT_SQL+ " AND UTP.updatedAt > :" +UPDATED_AT_PARAMETER;
	}
	
	@Override
	public String getPushedAfterByUserQuery() {
		return "SELECT DISTINCT UA FROM user_avatar UA LEFT JOIN UA.user.userToProjects " +LINKED_BY_PROJECT_SQL+ " AND UTP.updatedAt > :" +PUSHED_AT_PARAMETER;
	}

	@Override
	public String getPushedAfterByProjectQuery() {
		return "SELECT DISTINCT UA FROM user_avatar UA LEFT JOIN UA.user.userToProjects " +LINKED_BY_SINGLE_PROJECT_SQL+ " AND UTP.updatedAt > :" +PUSHED_AT_PARAMETER;
	}

}
