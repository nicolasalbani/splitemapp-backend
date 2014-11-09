package com.splitemapp.service.backendrest.dao;

import java.io.Serializable;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class DomainDAOTest {
	DomainDAO<SerializableStub> domainDao;
	
	@Before
	public void setUp() throws Exception {
		domainDao = new DomainDAO<SerializableStub>(){

			@Override
			public Class<SerializableStub> getEntityClass() {
				return SerializableStub.class;
			}
			
			@Override
			public String getTableName(String entityName){
				return super.getTableName(entityName);
			}
		};
	}
	
	@Test
	public void testGetTableName(){
		Assert.assertEquals("user", domainDao.getTableName("User"));
		Assert.assertEquals("expense_category", domainDao.getTableName("ExpenseCategory"));
		Assert.assertEquals("group", domainDao.getTableName("Group"));
		Assert.assertEquals("group_status", domainDao.getTableName("GroupStatus"));
		Assert.assertEquals("invite_status", domainDao.getTableName("InviteStatus"));
		Assert.assertEquals("project_status", domainDao.getTableName("ProjectStatus"));
		Assert.assertEquals("project", domainDao.getTableName("Project"));
		Assert.assertEquals("project_type", domainDao.getTableName("ProjectType"));
		Assert.assertEquals("user_contact_data", domainDao.getTableName("UserContactData"));
		Assert.assertEquals("user_expenses", domainDao.getTableName("UserExpenses"));
		Assert.assertEquals("user_invite", domainDao.getTableName("UserInvite"));
		Assert.assertEquals("user_status", domainDao.getTableName("UserStatus"));
		Assert.assertEquals("user_to_group", domainDao.getTableName("UserToGroup"));
		Assert.assertEquals("user_to_group_status", domainDao.getTableName("UserToGroupStatus"));
		Assert.assertEquals("user_to_project", domainDao.getTableName("UserToProject"));
		Assert.assertEquals("user_to_project_status", domainDao.getTableName("UserToProjectStatus"));
	}
	
	private class SerializableStub implements Serializable{
		private static final long serialVersionUID = 1L;
	}
}
