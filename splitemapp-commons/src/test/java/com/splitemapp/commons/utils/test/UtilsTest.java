package com.splitemapp.commons.utils.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;

import junit.framework.Assert;

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
			Assert.assertEquals(dateCal.getTime().toString(),Utils.stringToDate(date, ServiceConstants.DATE_FORMAT).toString());
		} catch (ParseException e) {
			Assert.fail("Should not be getting a ParseException!");
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
		Assert.assertEquals(date,Utils.dateToString(dateCal.getTime(), ServiceConstants.DATE_FORMAT).toString());
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
		Assert.assertEquals("user", Utils.getTableName("User"));
		Assert.assertEquals("expense_category", Utils.getTableName("ExpenseCategory"));
		Assert.assertEquals("group", Utils.getTableName("Group"));
		Assert.assertEquals("group_status", Utils.getTableName("GroupStatus"));
		Assert.assertEquals("invite_status", Utils.getTableName("InviteStatus"));
		Assert.assertEquals("project_status", Utils.getTableName("ProjectStatus"));
		Assert.assertEquals("project", Utils.getTableName("Project"));
		Assert.assertEquals("project_type", Utils.getTableName("ProjectType"));
		Assert.assertEquals("user_contact_data", Utils.getTableName("UserContactData"));
		Assert.assertEquals("user_expenses", Utils.getTableName("UserExpenses"));
		Assert.assertEquals("user_invite", Utils.getTableName("UserInvite"));
		Assert.assertEquals("user_status", Utils.getTableName("UserStatus"));
		Assert.assertEquals("user_to_group", Utils.getTableName("UserToGroup"));
		Assert.assertEquals("user_to_group_status", Utils.getTableName("UserToGroupStatus"));
		Assert.assertEquals("user_to_project", Utils.getTableName("UserToProject"));
		Assert.assertEquals("user_to_project_status", Utils.getTableName("UserToProjectStatus"));
		Assert.assertEquals("sync_status", Utils.getTableName("SyncStatus"));
	}
}
