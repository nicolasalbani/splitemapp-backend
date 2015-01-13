package com.splitemapp.service.backendrest.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;


public abstract class UpdateableDomainDAO <E extends Serializable,F extends Number> extends DomainDAO<E,F> {

	private static Logger logger = Logger.getLogger(UpdateableDomainDAO.class);

	protected static final String USER_ID_PARAMETER = "userId";
	protected static final String CREATED_AT_PARAMETER = "userId";
	protected static final String UPDATED_AT_PARAMETER = "userId";

	/**
	 * 
	 * @param session
	 * @return
	 */
	public abstract String getCreatedAfterQuery();
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	public abstract String getUpdatedAfterQuery();

	@SuppressWarnings("unchecked")
	public List<E> findCreatedAfter(Date createdAt, Long userId) {
		logger.debug("getting " +getEntityClass().getSimpleName()+ " instances created after " +createdAt+ " for userId " +userId);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery(getCreatedAfterQuery());
			query.setParameter(CREATED_AT_PARAMETER, createdAt);
			query.setParameter(USER_ID_PARAMETER, userId);
			List<E> resultList = query.list();
			if(resultList.size() > 0){
				logger.debug("get successful");
			} else {
				logger.debug("no record found created after " +createdAt);
			}
			return resultList;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> findUpdatedAfter(Date updatedAt, Long userId) {
		logger.debug("getting " +getEntityClass().getSimpleName()+ " instances created after " +updatedAt+ " for userId " +userId);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery(getUpdatedAfterQuery());
			query.setParameter(UPDATED_AT_PARAMETER, updatedAt);
			query.setParameter(USER_ID_PARAMETER, userId);
			List<E> resultList = query.list();
			if(resultList.size() > 0){
				logger.debug("get successful");
			} else {
				logger.debug("no record found updated after " +updatedAt);
			}
			return resultList;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

}
