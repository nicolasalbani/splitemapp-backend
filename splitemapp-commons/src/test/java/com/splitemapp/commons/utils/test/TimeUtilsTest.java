package com.splitemapp.commons.utils.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

import com.splitemapp.commons.utils.TimeUtils;

public class TimeUtilsTest {
	
	@Test
	public void testIsDateAfter(){
		Date todayDate = Calendar.getInstance().getTime();
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterdayDate = yesterday.getTime();
		assertTrue(TimeUtils.isDateAfter(todayDate, yesterdayDate));
	}
	
	@Test
	public void testGetUTCDateTime(){
		// Creating a DateTime object
		DateTime dateTime = new DateTime();
		
		// Comparing manually calculated time with the one coming from the method
		DateTime utcDateTime = TimeUtils.getUTCDateTime(dateTime);
		
		// Validating that time offset between the obtained UTC date is zero
		assertEquals(0,utcDateTime.getZone().getOffset(utcDateTime));
		
		// Validating that "milliseconds since 1970 in current time zone" match between the original and new DateTime objects
		assertEquals(dateTime.toDate().getTime(), utcDateTime.toDate().getTime());
	}

	@Test
	public void testGetDateTimeUTC(){
		assertTrue(TimeUtils.getUTCDateTime().getZone().getID().equals("UTC"));
	}
	
	@Test
	public void testGetDateTimeUTCWithDate(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		long dateUTC = TimeUtils.getUTCDate(calendar.getTime()).getTime();
		long dateLocal = calendar.getTimeInMillis();
		
		long difference = dateUTC - dateLocal;
		
		assertEquals(0,difference);
	}
}
