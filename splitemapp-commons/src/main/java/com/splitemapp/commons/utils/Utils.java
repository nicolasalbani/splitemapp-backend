package com.splitemapp.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class Utils {

	/**
	 * Converts the given Date to a String using the given date format
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String dateToString(Date date, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date); 
	}
	
	/**
	 * Converts the given String to a Date using the given date format
	 * @param date
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String date, String dateFormat) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.parse(date);
	}
	
	/**
	 * Hashes the password in SHA-1
	 * @param password
	 * @return
	 */
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
	
	/**
	 * Converts the entity name to its corresponding table name
	 * @param entityName
	 * @return
	 */
	public static String getTableName(String entityName){
		final int FIRST_CHARACTER = 0;
		
		StringBuilder tableName = new StringBuilder();
		
		if(entityName != null){
			 tableName.append(entityName);

			// First we change the first letter of the entity name to lower case
			tableName.replace(FIRST_CHARACTER, FIRST_CHARACTER+1, ""+Character.toLowerCase(tableName.charAt(FIRST_CHARACTER)));
			
			// We now replace every uppercase for <underscore>+<lowercase> to match table naming convention
			int length = tableName.length();
			for(int i = 1; i<length; i++){
				char charAt = tableName.charAt(i);
				if(Character.isUpperCase(charAt)){
					tableName.replace(i, i+1, "_"+Character.toLowerCase(tableName.charAt(i)));
				}
			}
		}
		
		return tableName.toString();
	}
	
	/**
	 * Calculates whether the left operand date is greater than the right operand date
	 * @param leftOperand
	 * @param rightOperand
	 * @return
	 */
	public static boolean isDateAfter(Date leftOperand, Date rightOperand){
		// We set the left operand
		Calendar leftOperandCal = Calendar.getInstance();
		leftOperandCal.setTime(leftOperand);
		
		// We set the right operand
		Calendar rightOperandCal = Calendar.getInstance();
		rightOperandCal.setTime(rightOperand);
		
		// We compare the returned values
		return leftOperandCal.compareTo(rightOperandCal)>0;
	}
}
