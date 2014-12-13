package com.splitemapp.service.backendrest.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;


public abstract class UpdateableDomainDAO <E extends Serializable> extends DomainDAO<E> {

	private static Logger logger = Logger.getLogger(UpdateableDomainDAO.class);

	protected static final String USER_ID_PARAMETER = "userId";

	/**
	 * Returns the required query to filter by the provided userId
	 * @return
	 */
	public abstract String getFilterByUserIdQuery();

	@SuppressWarnings("unchecked")
	public List<E> findCreatedAfter(Date createdAt, Long userId) {
		logger.debug("getting " +getEntityClass().getSimpleName()+ " instances created after " +createdAt+ " for userId " +userId);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("FROM " +getTableName()+ " E WHERE E.createdAt > :createdAt AND E.id IN (" +getFilterByUserIdQuery()+ ")");
			query.setParameter("createdAt", createdAt);
			query.setParameter(USER_ID_PARAMETER, userId);
			List<E> resultList = query.list();
			if(resultList.size() > 0){
				logger.debug("get successful");
				return resultList;
			} else {
				logger.debug("no record found created after " +createdAt);
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
	public List<E> findUpdatedAfter(Date updatedAt, Long userId) {
		logger.debug("getting " +getEntityClass().getSimpleName()+ " instances created after " +updatedAt+ " for userId " +userId);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("FROM " +getTableName()+ " E WHERE E.updatedAt > :updatedAt AND E.id IN (" +getFilterByUserIdQuery()+ ")");
			query.setParameter("updatedAt", updatedAt);
			query.setParameter(USER_ID_PARAMETER, userId);
			List<E> resultList = query.list();
			if(resultList.size() > 0){
				logger.debug("get successful");
				return resultList;
			} else {
				logger.debug("no record found updated after " +updatedAt);
				return null;
			}
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

}
