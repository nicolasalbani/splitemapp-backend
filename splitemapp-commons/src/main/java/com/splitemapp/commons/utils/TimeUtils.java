package com.splitemapp.commons.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TimeUtils {

	
	/**
	 * Calculates whether the left operand date is greater than the right operand date
	 * @param leftOperand
	 * @param rightOperand
	 * @return
	 */
	public static boolean isDateAfter(Date leftOperand, Date rightOperand){
		DateTime leftOperandDateTime = new DateTime(leftOperand);
		DateTime rightOperandDateTime = new DateTime(rightOperand);
		
		return leftOperandDateTime.compareTo(rightOperandDateTime) > 0;
	}
	
	/**
	 * Returns the current DateTime for the UTC time zone
	 * @return
	 */
	public static DateTime getDateTimeUTC(){
		return new DateTime(DateTimeZone.UTC);
	}
	
	/**
	 * Returns the current Date for the UTC time zone
	 * @return
	 */
	public static Date getDateUTC(){
		return new DateTime(DateTimeZone.UTC).toDate();
	}
	
}
