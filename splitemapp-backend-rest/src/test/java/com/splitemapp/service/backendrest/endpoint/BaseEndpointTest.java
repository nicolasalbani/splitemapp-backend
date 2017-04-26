package com.splitemapp.service.backendrest.endpoint;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.splitemapp.service.backendrest.dao.DomainDAO;

public abstract class BaseEndpointTest <E extends DomainDAO<F,G>, F extends Serializable, G extends Number> {
	
	E entityEndpoint = null;
	
	private static Logger logger = Logger.getLogger(BaseEndpointTest.class);

	@Before
	public void setUp() throws Exception {
		entityEndpoint = getEntityEndpoint();
	}
	
	@Test
	public void testFindByID() {
		logger.info("----------------------------------");
		logger.info(" testFindByID");
		logger.info("----------------------------------");
		
		F entity = entityEndpoint.findById(getExistingEntityId());
		assertNotNull(entity);
	}
	
	@Test
	public void testFindByIDFail() {
		logger.info("----------------------------------");
		logger.info(" testFindByIDFail");
		logger.info("----------------------------------");
		
		F entity = entityEndpoint.findById(getNonExistingEntityId());
		assertNull(entity);
	}
	
	@Test
	public void testPersist() {
		logger.info("----------------------------------");
		logger.info(" testPersist");
		logger.info("----------------------------------");
		
		F entityToSave = getEntityToSave();
		entityEndpoint.persist(entityToSave);
	}
	
	@Test
	public void testRemove() {
		logger.info("----------------------------------");
		logger.info(" testRemove");
		logger.info("----------------------------------");
		
		//TODO Implement this test
	}
	
	@Test
	public void testRemoveFail() {
		logger.info("----------------------------------");
		logger.info(" testRemoveFail");
		logger.info("----------------------------------");
		
		//TODO Implement this test
	}
	
	@Test
	public void testMerge() {
		logger.info("----------------------------------");
		logger.info(" testMerge");
		logger.info("----------------------------------");
		
		//TODO Implement this test
	}
	
	@Test
	public void testMergeFail() {
		logger.info("----------------------------------");
		logger.info(" testMergeFail");
		logger.info("----------------------------------");
		
		//TODO Implement this test
	}

	
	//Abstract methods to be defined in extending classes
	
	/**
	 * 
	 * @return E an instance of the entity endpoint
	 */
	protected abstract E getEntityEndpoint();
	
	/**
	 * 
	 * @return F and instance of the entity
	 */
	protected abstract F getEntityToSave();
	
	/**
	 * 
	 * @return an ID of an existing entity
	 */
	protected abstract G getExistingEntityId();
	
	/**
	 * 
	 * @return an ID of a non existing entity
	 */
	protected abstract G getNonExistingEntityId();
}
