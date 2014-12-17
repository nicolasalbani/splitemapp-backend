package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.Project;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class ProjectEndpoint extends UpdateableDomainDAO<Project> {

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT P FROM project P LEFT JOIN P.userToProjects UTP WHERE P.id = UTP.project.id AND UTP.user.id = :" +USER_ID_PARAMETER + " AND P.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT P FROM project P LEFT JOIN P.userToProjects UTP WHERE P.id = UTP.project.id AND UTP.user.id = :" +USER_ID_PARAMETER + " AND P.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}
