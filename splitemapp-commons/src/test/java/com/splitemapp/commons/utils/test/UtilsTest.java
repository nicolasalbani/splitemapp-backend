package com.splitemapp.commons.utils.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.domain.id.IdUpdateComparator;
import com.splitemapp.commons.utils.Utils;

public class UtilsTest {
	
	@Test
	public void testStringToDateAm(){
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
	public void testDateToStringAm() throws ParseException{
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
	public void testStringToDatePm(){
		String date = "31-08-1982 22:20:56";
		Calendar dateCal = Calendar.getInstance();
		dateCal.set(Calendar.YEAR, 1982);
		dateCal.set(Calendar.MONTH, 7);
		dateCal.set(Calendar.DAY_OF_MONTH, 31);
		dateCal.set(Calendar.HOUR_OF_DAY, 22);
		dateCal.set(Calendar.MINUTE, 20);
		dateCal.set(Calendar.SECOND, 56);
		try {
			assertEquals(dateCal.getTime().toString(),Utils.stringToDate(date, ServiceConstants.DATE_FORMAT).toString());
		} catch (ParseException e) {
			fail("Should not be getting a ParseException!");
		}
	}
	
	@Test
	public void testDateToStringPm() throws ParseException{
		String date = "31-08-1982 22:20:56";
		Calendar dateCal = Calendar.getInstance();
		dateCal.set(Calendar.YEAR, 1982);
		dateCal.set(Calendar.MONTH, 7);
		dateCal.set(Calendar.DAY_OF_MONTH, 31);
		dateCal.set(Calendar.HOUR_OF_DAY, 22);
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
	
	@Test
	public void testGetEntityName(){
		assertEquals("User", Utils.getEntityName("user"));
		assertEquals("ExpenseCategory", Utils.getEntityName("expense_category"));
		assertEquals("Group", Utils.getEntityName("group"));
		assertEquals("GroupStatus", Utils.getEntityName("group_status"));
		assertEquals("InviteStatus", Utils.getEntityName("invite_status"));
		assertEquals("ProjectStatus", Utils.getEntityName("project_status"));
		assertEquals("Project", Utils.getEntityName("project"));
		assertEquals("ProjectType", Utils.getEntityName("project_type"));
		assertEquals("UserContactData", Utils.getEntityName("user_contact_data"));
		assertEquals("UserExpenses", Utils.getEntityName("user_expenses"));
		assertEquals("UserInvite", Utils.getEntityName("user_invite"));
		assertEquals("UserStatus", Utils.getEntityName("user_status"));
		assertEquals("UserToGroup", Utils.getEntityName("user_to_group"));
		assertEquals("UserToGroupStatus", Utils.getEntityName("user_to_group_status"));
		assertEquals("UserToProject", Utils.getEntityName("user_to_project"));
		assertEquals("UserToProjectStatus", Utils.getEntityName("user_to_project_status"));
		assertEquals("SyncStatus", Utils.getEntityName("sync_status"));
	}
	
	@Test
	public void testGetCamelCaseName(){
		assertEquals("user", Utils.getCamelCaseName("user"));
		assertEquals("expenseCategory", Utils.getCamelCaseName("expense_category"));
		assertEquals("group", Utils.getCamelCaseName("group"));
		assertEquals("groupStatus", Utils.getCamelCaseName("group_status"));
		assertEquals("inviteStatus", Utils.getCamelCaseName("invite_status"));
		assertEquals("projectStatus", Utils.getCamelCaseName("project_status"));
		assertEquals("project", Utils.getCamelCaseName("project"));
		assertEquals("projectType", Utils.getCamelCaseName("project_type"));
		assertEquals("userContactData", Utils.getCamelCaseName("user_contact_data"));
		assertEquals("userExpenses", Utils.getCamelCaseName("user_expenses"));
		assertEquals("userInvite", Utils.getCamelCaseName("user_invite"));
		assertEquals("userStatus", Utils.getCamelCaseName("user_status"));
		assertEquals("userToGroup", Utils.getCamelCaseName("user_to_group"));
		assertEquals("userToGroupStatus", Utils.getCamelCaseName("user_to_group_status"));
		assertEquals("userToProject", Utils.getCamelCaseName("user_to_project"));
		assertEquals("userToProjectStatus", Utils.getCamelCaseName("user_to_project_status"));
		assertEquals("syncStatus", Utils.getCamelCaseName("sync_status"));
	}
	
	@Test
	public void testIdUpdateComparator(){
		List<IdUpdate<Long>> idUpdateList = new ArrayList<IdUpdate<Long>>();
		
		idUpdateList.add(new IdUpdate<Long>(4l, 4l));
		idUpdateList.add(new IdUpdate<Long>(1l, 1l));
		idUpdateList.add(new IdUpdate<Long>(5l, 5l));
		idUpdateList.add(new IdUpdate<Long>(8l, 8l));
		
		Collections.sort(idUpdateList, new IdUpdateComparator());
		
		// Validating correct order
		assertEquals(new Long(8), idUpdateList.get(0).getOldId());
		assertEquals(new Long(5), idUpdateList.get(1).getOldId());
		assertEquals(new Long(4), idUpdateList.get(2).getOldId());
		assertEquals(new Long(1), idUpdateList.get(3).getOldId());
	}
}
