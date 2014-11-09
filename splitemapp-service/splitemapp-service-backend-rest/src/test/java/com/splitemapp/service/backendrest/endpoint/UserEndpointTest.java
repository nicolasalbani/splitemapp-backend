package com.splitemapp.service.backendrest.endpoint;

import com.splitemapp.domainmodel.domain.User;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;

public class UserEndpointTest extends BaseEndpointTest<UserEndpoint, User>{

	@Override
	protected UserEndpoint getEntityEndpoint() {
		return new UserEndpoint();
	}

	@Override
	protected User getEntityToSave() {
		return EntityCreator.createUserEntity(1L);
	}

	@Override
	protected Long getExistingEntityId() {
		return 1L;
	}

	@Override
	protected Long getNonExistingEntityId() {
		return 2L;
	}
}