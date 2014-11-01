package com.fairpay.backendrest.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.splitemapp.service.backendrest.utils.BackendUtils;

public class BackendUtilsTest {
	
	@Test
	public void testHashPassword(){
		String password = "1234567890";
		assertEquals("01b307acba4f54f55aafc33bb06bbbf6ca803e9a", BackendUtils.hashPassword(password));
	}

}
