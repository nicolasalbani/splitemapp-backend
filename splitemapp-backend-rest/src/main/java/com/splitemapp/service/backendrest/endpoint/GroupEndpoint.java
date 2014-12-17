package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.Group;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class GroupEndpoint extends UpdateableDomainDAO<Group> {

	@Override
	public Class<Group> getEntityClass() {
		return Group.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT G FROM group G LEFT JOIN G.userToGroups UTG WHERE G.id = UTG.group.id AND UTG.user.id = :" +USER_ID_PARAMETER + " AND G.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT G FROM group G LEFT JOIN G.userToGroups UTG WHERE G.id = UTG.group.id AND UTG.user.id = :" +USER_ID_PARAMETER + " AND G.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}
