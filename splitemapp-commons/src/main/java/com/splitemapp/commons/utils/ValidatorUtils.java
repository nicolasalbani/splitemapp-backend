package com.splitemapp.commons.utils;

import com.splitemapp.commons.validator.EmailValidator;

public class ValidatorUtils {
	
	public static boolean isValidEmail(String email){
		EmailValidator instance = EmailValidator.getInstance();
		return instance.isValid(email);
	}

}
