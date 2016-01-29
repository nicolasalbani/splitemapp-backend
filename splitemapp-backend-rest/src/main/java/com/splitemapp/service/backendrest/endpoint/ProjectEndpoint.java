package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.Project;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class ProjectEndpoint extends UpdateableDomainDAO<Project,Long> {

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT P FROM project P LEFT JOIN P.userToProjects UTP WHERE P.id = UTP.project.id AND UTP.user.id = :" +USER_ID_PARAMETER + " AND P.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT P FROM project P LEFT JOIN P.userToProjects UTP WHERE P.id = UTP.project.id AND UTP.user.id = :" +USER_ID_PARAMETER + " AND P.updatedAt > :" +UPDATED_AT_PARAMETER;
	}
	
	@Override
	public String getPushedAfterByUserQuery() {
		return "SELECT DISTINCT P FROM project P LEFT JOIN P.userToProjects UTP WHERE P.id = UTP.project.id AND UTP.user.id = :" +USER_ID_PARAMETER + " AND P.pushedAt > :" +PUSHED_AT_PARAMETER;
	}

	@Override
	public String getPushedAfterByProjectQuery() {
		return "SELECT P FROM project P WHERE P.id = :" +PROJECT_ID_PARAMETER;
	}

}
