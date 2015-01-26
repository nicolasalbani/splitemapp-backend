package com.splitemapp.commons.utils.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.utils.Utils;

public class UtilsTest {
	
	@Test
	public void testStringToDate(){
		String date = "31-08-1982 10:20:56";
		Calendar dateCal = Calendar.getInstance();
		dateCal.set(Calendar.YEAR, 1982);
		dateCal.set(Calendar.MONTH, 7);
		dateCal.set(Calendar.DAY_OF_MONTH, 31);
		dateCal.set(Calendar.HOUR_OF_DAY, 10);
		dateCal.set(Calendar.MINUTE, 20);
		dateCal.set(Calendar.SECOND, 56);
		try {
			assertEquals(dateCal.getTime().toString(),Utils.stringToDate(date, ServiceConstants.DATE_FORMAT).toString());
		} catch (ParseException e) {
			fail("Should not be getting a ParseException!");
		}
	}
	
	@Test
	public void testDateToString() throws ParseException{
		String date = "31-08-1982 10:20:56";
		Calendar dateCal = Calendar.getInstance();
		dateCal.set(Calendar.YEAR, 1982);
		dateCal.set(Calendar.MONTH, 7);
		dateCal.set(Calendar.DAY_OF_MONTH, 31);
		dateCal.set(Calendar.HOUR_OF_DAY, 10);
		dateCal.set(Calendar.MINUTE, 20);
		dateCal.set(Calendar.SECOND, 56);
		assertEquals(date,Utils.dateToString(dateCal.getTime(), ServiceConstants.DATE_FORMAT));
	}
	
	@Test
	public void testIsDateAfter(){
		Date todayDate = Calendar.getInstance().getTime();
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterdayDate = yesterday.getTime();
		assertTrue(Utils.isDateAfter(todayDate, yesterdayDate));
	}
	
	@Test
	public void testHashPassword(){
		String password = "1234567890";
		assertEquals("01b307acba4f54f55aafc33bb06bbbf6ca803e9a", Utils.hashPassword(password));
		password = "123";
		assertEquals("40bd001563085fc35165329ea1ff5c5ecbdbbeef", Utils.hashPassword(password));
	}

	@Test
	public void testGetTableName(){
		assertEquals("user", Utils.getTableName("User"));
		assertEquals("expense_category", Utils.getTableName("ExpenseCategory"));
		assertEquals("group", Utils.getTableName("Group"));
		assertEquals("group_status", Utils.getTableName("GroupStatus"));
		assertEquals("invite_status", Utils.getTableName("InviteStatus"));
		assertEquals("project_status", Utils.getTableName("ProjectStatus"));
		assertEquals("project", Utils.getTableName("Project"));
		assertEquals("project_type", Utils.getTableName("ProjectType"));
		assertEquals("user_contact_data", Utils.getTableName("UserContactData"));
		assertEquals("user_expenses", Utils.getTableName("UserExpenses"));
		assertEquals("user_invite", Utils.getTableName("UserInvite"));
		assertEquals("user_status", Utils.getTableName("UserStatus"));
		assertEquals("user_to_group", Utils.getTableName("UserToGroup"));
		assertEquals("user_to_group_status", Utils.getTableName("UserToGroupStatus"));
		assertEquals("user_to_project", Utils.getTableName("UserToProject"));
		assertEquals("user_to_project_status", Utils.getTableName("UserToProjectStatus"));
		assertEquals("sync_status", Utils.getTableName("SyncStatus"));
	}
}
