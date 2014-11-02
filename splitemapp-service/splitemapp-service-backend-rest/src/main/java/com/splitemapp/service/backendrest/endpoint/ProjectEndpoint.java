package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.service.domainmodel.domain.Project;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class ProjectEndpoint extends DomainDAO<Project> {

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

}
