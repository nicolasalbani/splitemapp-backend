package com.splitemapp.service.backendrest.services;

import java.util.ArrayList;
import java.util.List;

import com.splitemapp.commons.constants.ServiceConstants;

public class UserServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.PULL_USERS_PATH);
		servicePathList.add(ServiceConstants.PUSH_USERS_PATH);
		return servicePathList;
	}

}
