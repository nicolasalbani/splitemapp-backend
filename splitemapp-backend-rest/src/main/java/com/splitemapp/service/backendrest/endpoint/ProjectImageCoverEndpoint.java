package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.commons.domain.ProjectImageCover;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class ProjectImageCoverEndpoint extends UpdateableDomainDAO<ProjectImageCover,Long> {

	@Override
	public Class<ProjectImageCover> getEntityClass() {
		return ProjectImageCover.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT PIC FROM project_image_cover PIC WHERE PIC.projectId = :" +PROJECT_ID_PARAMETER + " AND PIC.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT PIC FROM project_image_cover PIC WHERE PIC.projectId = :" +PROJECT_ID_PARAMETER + " AND PIC.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

}
