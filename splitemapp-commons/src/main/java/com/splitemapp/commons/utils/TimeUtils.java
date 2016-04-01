package com.splitemapp.commons.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

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
	 * Returns the provided DateTime for the UTC time zone
	 * @return
	 */
	public static DateTime getUTCDateTime(DateTime dateTime){
		// Getting the local time zone
        DateTimeZone localTZ = DateTimeZone.getDefault();

        // Converting the provided DateTime object to UTC  
        long eventMillsInUTCTimeZone = localTZ.convertLocalToUTC(dateTime.getMillis(), false);

        // Creating a new DateTime object with the time in UTC and with UTC timezone
		return new LocalDateTime(eventMillsInUTCTimeZone).toDateTime(DateTimeZone.UTC);
	}
	
	/**
	 * Returns the current DateTime for the UTC time zone
	 * @return
	 */
	public static DateTime getUTCDateTime(){
		// Getting the current dateTime
		DateTime dateTime = new DateTime();
		
		// Getting the local time zone
        DateTimeZone localTZ = DateTimeZone.getDefault();

        // Converting the provided DateTime object to UTC  
        long eventMillsInUTCTimeZone = localTZ.convertLocalToUTC(dateTime.getMillis(), false);

        // Creating a new DateTime object with the time in UTC and with UTC timezone
		return new LocalDateTime(eventMillsInUTCTimeZone).toDateTime(DateTimeZone.UTC);
	}
	
	/**
	 * Returns the current Date for the UTC time zone
	 * @return
	 */
	public static Date getUTCDate(){
		return getUTCDateTime().toDate();
	}
	
	/**
	 * Returns the provided Date converted to UTC
	 * @return
	 */
	public static Date getUTCDate(Date date){
		// Creating a new DateTime object based on the provided date
		DateTime dateTime = new DateTime(date);
		
		return getUTCDateTime(dateTime).toDate();
	}
	
}
