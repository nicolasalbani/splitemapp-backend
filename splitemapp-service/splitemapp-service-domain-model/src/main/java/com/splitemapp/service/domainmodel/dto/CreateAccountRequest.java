package com.splitemapp.service.domainmodel.dto;

import javax.xml.bind.annotation.XmlType;

import com.splitemapp.commons.constants.ServiceConstants;

@XmlType(name=ServiceConstants.CREATE_ACCOUNT_REQUEST_ROOT)
@javax.xml.bind.annotation.XmlRootElement(name=ServiceConstants.CREATE_ACCOUNT_REQUEST_ROOT)
@javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class CreateAccountRequest extends com.splitemapp.commons.domain.dto.request.CreateAccountRequest{
	
}
