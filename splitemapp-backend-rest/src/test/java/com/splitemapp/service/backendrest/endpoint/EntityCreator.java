package com.splitemapp.service.backendrest.endpoint;

import java.util.Date;

import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserStatus;

public class EntityCreator {

	public static User createUserEntity(long existingUserId){
		// Creating new user
		User user = new User();
		user.setCreatedAt(new Date());
		user.setCreatedIpAddress("127.0.0.1");
		user.setFullName("pablo marmol");
		user.setLastLogin(new Date());
		user.setLoginCnt(0);
		user.setPassword("!$#&!#$&!#$&!#$");
		user.setUpdatedAt(new Date());
		user.setUpdatedIpAddress("127.0.0.1");
		user.setUsername("pablomarmol");
		
		// Getting active status from DB
		UserStatusEndpoint userStatusEndpoint = new UserStatusEndpoint();
		UserStatus userStatus = userStatusEndpoint.findById((short)1);

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
