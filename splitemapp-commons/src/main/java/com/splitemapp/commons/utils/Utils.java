package com.splitemapp.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Utils {

	public static String hashPassword(String password) {
		String newPassword=null;
		if (password != null && !password.isEmpty()) {
			MessageDigest md;
			Formatter formatter = null;
			try {
				md = MessageDigest.getInstance("SHA-1");
				byte[] hash = md.digest(password.getBytes());
				formatter = new Formatter();
				for (byte b : hash) {
					formatter.format("%02x", b);
				}
				newPassword = formatter.toString();
			} 
			catch (NoSuchAlgorithmException e) {
				throw new HashPasswordException("Can't hash Password",e);
			}finally{
				if(formatter != null){
					formatter.close();
				}
			}
		} 
		return newPassword;
	}
}
