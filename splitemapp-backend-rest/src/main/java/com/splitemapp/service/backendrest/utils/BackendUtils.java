package com.splitemapp.service.backendrest.utils;

import java.util.UUID;

public class BackendUtils {

	public static String createSessionToken(){
		UUID token = UUID.randomUUID();
		
		return token.toString();
	}
}
