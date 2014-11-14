package com.splitemapp.commons.utils.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.splitemapp.commons.utils.Utils;

public class UtilsTest {
	
	@Test
	public void testHashPassword(){
		String password = "1234567890";
		assertEquals("01b307acba4f54f55aafc33bb06bbbf6ca803e9a", Utils.hashPassword(password));
	}

}
