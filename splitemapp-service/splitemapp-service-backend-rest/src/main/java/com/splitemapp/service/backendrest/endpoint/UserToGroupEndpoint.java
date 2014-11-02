package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.service.domainmodel.domain.UserToProject;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserToGroupEndpoint extends DomainDAO<UserToProject> {

	@Override
	public Class<UserToProject> getEntityClass() {
		return UserToProject.class;
	}

}
