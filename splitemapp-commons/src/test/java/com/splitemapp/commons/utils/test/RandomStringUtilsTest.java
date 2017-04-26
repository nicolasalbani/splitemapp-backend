package com.splitemapp.commons.utils.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.splitemapp.commons.utils.RandomStringUtils;

public class RandomStringUtilsTest {

	@Test
	public void testGetRandomString() {
		String randomString = RandomStringUtils.getRandomString(8);
		assertEquals(8, randomString.length());
		
		randomString = RandomStringUtils.getRandomString(10);
		assertEquals(10, randomString.length());
		
		randomString = RandomStringUtils.getRandomString(20);
		assertEquals(20, randomString.length());
		
		randomString = RandomStringUtils.getRandomString(100);
		assertEquals(100, randomString.length());
	}

}
