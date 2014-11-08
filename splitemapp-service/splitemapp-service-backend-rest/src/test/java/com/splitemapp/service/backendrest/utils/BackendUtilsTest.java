package com.splitemapp.service.backendrest.utils;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

public class BackendUtilsTest {
	
	@Test
	public void testHashPassword(){
		String password = "1234567890";
		assertEquals("01b307acba4f54f55aafc33bb06bbbf6ca803e9a", BackendUtils.hashPassword(password));
	}

	@Test
	public void testCreateSessionToken(){
		final int TOKEN_AMOUNT = 100000;
		
		HashSet<String> tokenSet = new HashSet<String>();
		for(int i=0;i<TOKEN_AMOUNT;i++){
			Assert.assertTrue("Tokens should be unique!", tokenSet.add(BackendUtils.createSessionToken()));
		}
	}
}
