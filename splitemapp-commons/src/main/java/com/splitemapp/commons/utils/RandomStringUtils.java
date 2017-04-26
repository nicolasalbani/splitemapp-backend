package com.splitemapp.commons.utils;

import java.util.Random;

public class RandomStringUtils {

	private static final char[] symbols;

	static {
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch)
			tmp.append(ch);
		for (char ch = 'a'; ch <= 'z'; ++ch)
			tmp.append(ch);
		symbols = tmp.toString().toCharArray();
	}

	/**
	 * Returns a random string of the provided length
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length){
		Random random = new Random();
		char[] buf;

		// Validating input
		if (length < 1){
			throw new IllegalArgumentException("length < 1: " + length);
		}

		buf = new char[length];

		for (int idx = 0; idx < buf.length; ++idx) 
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}

}
