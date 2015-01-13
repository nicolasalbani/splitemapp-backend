package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.GroupStatus;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class GroupStatusEndpoint extends DomainDAO<GroupStatus,Short> {

	@Override
	public Class<GroupStatus> getEntityClass() {
		return GroupStatus.class;
	}

}
