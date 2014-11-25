package com.splitemapp.service.backendrest.dao;

import java.io.Serializable;

import org.junit.Before;

public class DomainDAOTest {
	DomainDAO<SerializableStub> domainDao;
	
	@Before
	public void setUp() throws Exception {
		domainDao = new DomainDAO<SerializableStub>(){

			@Override
			public Class<SerializableStub> getEntityClass() {
				return SerializableStub.class;
			}
		};
	}

	
	private class SerializableStub implements Serializable{
		private static final long serialVersionUID = 1L;
	}
}
