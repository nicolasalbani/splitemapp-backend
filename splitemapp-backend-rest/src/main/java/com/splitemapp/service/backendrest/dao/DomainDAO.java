package com.splitemapp.service.backendrest.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.provider.SessionFactoryProvider;


public abstract class DomainDAO <E extends Serializable, F extends Number> {

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
	
	@SuppressWarnings("unchecked")
	public F save(E transientInstance) {
		logger.debug("saving " +transientInstance.getClass().getSimpleName()+ " instance");
		
		Session session = sessionFactory.openSession();
		F id = null;
		try {
			id = (F)session.save(transientInstance);
			logger.debug("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		} finally {
			session.close();
		}
		
		return id;
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
	public E findById(F id) {
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
	
	@SuppressWarnings("unchecked")
	public E findByField(String fieldName, String fieldValue) {
		logger.debug("getting " +getEntityClass().getSimpleName()+ " instance with " +fieldName+ ": " + fieldValue);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("FROM " +getTableName()+ " E WHERE E." +fieldName+ " = :" +fieldName);
			query.setParameter(fieldName, fieldValue);
			List<E> resultList = query.list();
			if(resultList.size() == 1){
				logger.debug("get successful");
				return resultList.get(0);
			} else if (resultList.size() > 1){
				throw new RuntimeException("more than one record with the same " +fieldName);
			} else {
				logger.debug("no record found for " +fieldName+ ": " +fieldValue);
				return null;
			}
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public E findByFieldWithJoins(String fieldName, String fieldValue, String... joins) {
		logger.debug("getting " +getEntityClass().getSimpleName()+ " instance with " +fieldName+ ": " + fieldValue);

		Session session = sessionFactory.openSession();
		try {
			StringBuilder queryString = new StringBuilder("SELECT DISTINCT E FROM " +getTableName()+ " E");
			for(String join:joins){
				queryString.append(" LEFT JOIN FETCH E."+join);
			}
			queryString.append(" WHERE E." +fieldName+ " = :" +fieldName);
			Query query = session.createQuery(queryString.toString());
			query.setParameter(fieldName, fieldValue);
			List<E> resultList = query.list();
			if(resultList.size() == 1){
				logger.debug("get successful");
				return resultList.get(0);
			} else if (resultList.size() > 1){
				throw new RuntimeException("more than one record with the same " +fieldName);
			} else {
				logger.debug("no record found for " +fieldName+ ": " +fieldValue);
				return null;
			}
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findWithQuery(String sqlQuery) {
		logger.debug("Running Query: " +sqlQuery);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery(sqlQuery);
			List<E> resultList = query.list();
			if(resultList.size() > 0){
				logger.debug("get successful");
			} else {
				logger.debug("no record found for custom query");
			}
			return resultList;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} finally {
			session.close();
		}
	}
	
	protected String getTableName(){
		return Utils.getTableName(getEntityClass().getSimpleName());
	}
	
}
