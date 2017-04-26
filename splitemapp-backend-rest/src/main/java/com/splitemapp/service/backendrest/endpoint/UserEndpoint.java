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
		return "SELECT DISTINCT U FROM user U LEFT JOIN U.userToProjects " +LINKED_BY_PROJECT_SQL+ " AND UTP.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT U FROM user U LEFT JOIN U.userToProjects " +LINKED_BY_PROJECT_SQL+ " AND UTP.updatedAt > :" +UPDATED_AT_PARAMETER;
	}
	
	@Override
	public String getPushedAfterByUserQuery() {
		return "SELECT DISTINCT U FROM user U LEFT JOIN U.userToProjects " +LINKED_BY_PROJECT_SQL+ " AND UTP.pushedAt > :" +PUSHED_AT_PARAMETER;
	}
	
	@Override
	public String getPushedAfterByProjectQuery() {
		return "SELECT DISTINCT U FROM user U LEFT JOIN U.userToProjects " +LINKED_BY_SINGLE_PROJECT_SQL+ " AND UTP.pushedAt > :" +PUSHED_AT_PARAMETER;
	}
	
	public User findUserForLogin(String fieldName, String fieldValue){
		return findByFieldWithJoins(fieldName, fieldValue, "userContactDatas", "userAvatars");
	}
	
	public User findUserForSyncContacts(String fieldName, String fieldValue){
		return findByFieldWithJoins(fieldName, fieldValue, "userContactDatas", "userAvatars");
	}


}
