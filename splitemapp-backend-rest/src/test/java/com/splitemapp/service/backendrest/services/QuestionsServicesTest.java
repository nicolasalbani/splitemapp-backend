package com.splitemapp.service.backendrest.services;

import java.util.ArrayList;
import java.util.List;

import com.splitemapp.commons.constants.ServiceConstants;

public class QuestionsServicesTest extends BaseServiceTest{

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.QUESTIONS_PATH);
		return servicePathList;
	}

}
