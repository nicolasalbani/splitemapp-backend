package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.ProjectStatus;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class ProjectStatusEndpoint extends DomainDAO<ProjectStatus,Short> {

	@Override
	public Class<ProjectStatus> getEntityClass() {
		return ProjectStatus.class;
	}

}
