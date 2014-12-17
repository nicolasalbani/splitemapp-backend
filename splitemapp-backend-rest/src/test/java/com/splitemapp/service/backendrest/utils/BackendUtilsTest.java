package com.splitemapp.service.backendrest.utils;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

public class BackendUtilsTest {
	
	@Test
	public void testCreateSessionToken(){
		final int TOKEN_AMOUNT = 100000;
		
		HashSet<String> tokenSet = new HashSet<String>();
		for(int i=0;i<TOKEN_AMOUNT;i++){
			Assert.assertTrue("Tokens should be unique!", tokenSet.add(BackendUtils.createSessionToken()));
		}
	}
}
