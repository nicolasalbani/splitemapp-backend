package com.fairpay.backendrest.endpoint;

import java.util.Date;

import com.fairpay.domainmodel.domain.User;
import com.fairpay.domainmodel.domain.UserStatus;
import com.splitemapp.service.backendrest.endpoint.UserEndpoint;

public class EntityCreator {

	public static User createUserEntity(long existingUserId){
		User user = new User();
		user.setCreatedAt(new Date());
		user.setCreatedIpAddress("127.0.0.1");
		user.setFirstName("pablo");
		user.setLastLogin(new Date());
		user.setLastName("marmol");
		user.setLoginCnt(0);
		user.setPassword("!$#&!#$&!#$&!#$");
		user.setUpdatedAt(new Date());
		user.setUpdatedIpAddress("127.0.0.1");
		user.setUsername("pablomarmol");
		
		UserEndpoint userEndpoint = new UserEndpoint();
		UserStatus userStatus = userEndpoint.findById(existingUserId).getUserStatus();
		
		user.setUserStatus(userStatus);
		return user;
	}
	
	public static UserStatus createUserContactDataEntity(){
		UserStatus userStatus = new UserStatus();
		userStatus.setCod("new_user_status");
		userStatus.setTitle("Nuevo Estado de Usuario");
		
		return userStatus;
	}
}
