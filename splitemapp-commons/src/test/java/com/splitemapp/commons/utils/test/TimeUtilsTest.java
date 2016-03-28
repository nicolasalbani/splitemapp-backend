package com.splitemapp.commons.utils.test;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

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
	public void testGetDateTimeUTC(){
		assertTrue(TimeUtils.getDateTimeUTC().getZone().getID().equals("UTC"));
	}
}
