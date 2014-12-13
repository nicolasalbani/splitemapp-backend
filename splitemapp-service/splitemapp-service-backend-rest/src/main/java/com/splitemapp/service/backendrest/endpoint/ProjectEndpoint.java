package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.Project;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class ProjectEndpoint extends UpdateableDomainDAO<Project> {

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

	@Override
	public String getFilterByUserIdQuery() {
		return "SELECT project_id FROM user_to_project U WHERE U.user = :" +USER_ID_PARAMETER;
	}

}
