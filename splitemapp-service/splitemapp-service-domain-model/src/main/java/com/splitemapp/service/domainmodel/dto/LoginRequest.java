package com.splitemapp.service.domainmodel.dto;

import javax.xml.bind.annotation.XmlType;

import com.splitemapp.commons.constants.ServiceConstants;

@XmlType(name=ServiceConstants.LOGIN_REQUEST_ROOT)
@javax.xml.bind.annotation.XmlRootElement(name=ServiceConstants.LOGIN_REQUEST_ROOT)
@javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class LoginRequest extends com.splitemapp.commons.domain.dto.request.LoginRequest{
	
}
