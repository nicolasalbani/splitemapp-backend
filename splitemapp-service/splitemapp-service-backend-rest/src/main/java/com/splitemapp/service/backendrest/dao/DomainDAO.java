package com.splitemapp.service.backendrest.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.splitemapp.service.domainmodel.provider.SessionFactoryProvider;


public abstract class DomainDAO <E extends Serializable> {

private static Logger logger = Logger.getLogger(DomainDAO.class);
	
	protected SessionFactory sessionFactory = null;
	
	public DomainDAO(){
		sessionFactory = SessionFactoryProvider.getSessionFactory();
	}
	
	//We force the extending class to implement this method
	public abstract Class<E> getEntityClass();

	public void persist(E transientInstance) {
		logger.debug("persisting " +transientInstance.getClass().getSimpleName()+ " instance");
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(transientInstance);
			tx.commit();
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			if(tx!=null) tx.rollback();
			logger.error("persist failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

	public void remove(E persistentInstance) {
		logger.debug("removing " +getEntityClass().getSimpleName()+ " instance");
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			if(tx!=null) tx.rollback();
			logger.error("remove failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public E merge(E detachedInstance) {
		logger.debug("merging " +getEntityClass().getSimpleName()+ " instance");
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			E result = (E) session.merge(detachedInstance);
			tx.commit();
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			if(tx!=null) tx.rollback();
			logger.error("merge failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public E findById(Long id) {
		logger.debug("getting " +getEntityClass().getSimpleName()+ " instance with id: " + id);
		
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("FROM " +getTableName()+ " E WHERE E.id = :id");
			query.setParameter("id", id);
			List<E> resultList = query.list();
			if(resultList.size() == 1){
				logger.debug("get successful");
				return resultList.get(0);
			} else if (resultList.size() > 1){
				throw new RuntimeException("more than one record with the same ID!");
			} else {
				logger.debug("no record found for id: " +id);
				return null;
			}
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} finally {
			session.close();
		}
	}
	
	protected String getTableName(){
		return getTableName(getEntityClass().getSimpleName());
	}
	
	protected String getTableName(String entityName){
		final int FIRST_CHARACTER = 0;
		
		StringBuilder tableName = new StringBuilder();
		
		if(entityName != null){
			 tableName.append(entityName);

			// First we change the first letter of the entity name to lower case
			tableName.replace(FIRST_CHARACTER, FIRST_CHARACTER+1, ""+Character.toLowerCase(tableName.charAt(FIRST_CHARACTER)));
			
			// We now replace every uppercase for <underscore>+<lowercase> to match table naming convention
			int length = tableName.length();
			for(int i = 1; i<length; i++){
				char charAt = tableName.charAt(i);
				if(Character.isUpperCase(charAt)){
					tableName.replace(i, i+1, "_"+Character.toLowerCase(tableName.charAt(i)));
				}
			}
		}
		
		return tableName.toString();
	}
}
