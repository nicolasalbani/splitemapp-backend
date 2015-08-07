package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.ProjectCoverImage;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class ProjectCoverImageEndpoint extends UpdateableDomainDAO<ProjectCoverImage,Long> {

	@Override
	public Class<ProjectCoverImage> getEntityClass() {
		return ProjectCoverImage.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT PCI FROM project_cover_image PCI WHERE PCI.projectId = :" +PROJECT_ID_PARAMETER + " AND PCI.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT PCI FROM project_cover_image PCI WHERE PCI.projectId = :" +PROJECT_ID_PARAMETER + " AND PCI.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}