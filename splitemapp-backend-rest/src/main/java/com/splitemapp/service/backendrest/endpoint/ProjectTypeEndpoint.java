package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.ProjectType;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class ProjectTypeEndpoint extends DomainDAO<ProjectType,Short> {

	@Override
	public Class<ProjectType> getEntityClass() {
		return ProjectType.class;
	}

}
