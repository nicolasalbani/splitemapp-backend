package com.splitemapp.commons.utils.test;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;

import com.splitemapp.commons.utils.Utils;

public class UtilsTest {
	
	@Test
	public void testHashPassword(){
		String password = "1234567890";
		assertEquals("01b307acba4f54f55aafc33bb06bbbf6ca803e9a", Utils.hashPassword(password));
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
