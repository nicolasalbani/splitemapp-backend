package com.splitemapp.service.backendrest.services;

import java.util.ArrayList;
import java.util.List;

import com.splitemapp.commons.constants.ServiceConstants;

public class ChangePasswordServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.CHANGE_PASSWORD_PATH);
		return servicePathList;
	}

}
