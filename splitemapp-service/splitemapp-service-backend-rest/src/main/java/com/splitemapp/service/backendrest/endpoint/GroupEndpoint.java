package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.Group;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class GroupEndpoint extends DomainDAO<Group> {

	@Override
	public Class<Group> getEntityClass() {
		return Group.class;
	}

}
