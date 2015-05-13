package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.InviteStatus;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class InviteStatusEndpoint extends DomainDAO<InviteStatus, Short> {

	@Override
	public Class<InviteStatus> getEntityClass() {
		return InviteStatus.class;
	}

}
