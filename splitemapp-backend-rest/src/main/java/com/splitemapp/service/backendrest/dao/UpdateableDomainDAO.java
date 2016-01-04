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
	protected static final String PROJECT_ID_PARAMETER = "projectId";
	protected static final String CREATED_AT_PARAMETER = "createdAt";
	protected static final String UPDATED_AT_PARAMETER = "updatedAt";
	protected static final String PUSHED_AT_PARAMETER = "pushedAt";
	protected static final String LINKED_BY_PROJECT_SQL = "UTP WHERE UTP.project.id IN (SELECT DISTINCT UTP.project.id FROM user_to_project UTP WHERE UTP.user.id = :" +USER_ID_PARAMETER+ ")";

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

	/**
	 * 
	 * @param session
	 * @return
	 */
	public abstract String getPushedAfterQuery();

	@SuppressWarnings("unchecked")
	public List<E> findCreatedAfter(Date createdAt, Long userId) {

		String gettingMessage = "getting " +getEntityClass().getSimpleName() +" instances for userId " +userId;

		if(createdAt != null){
			gettingMessage +=  " created after " +createdAt;
		}

		logger.debug(gettingMessage);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery(getCreatedAfterQuery());
			if(createdAt != null){
				query.setParameter(CREATED_AT_PARAMETER, createdAt);
			}
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
		String gettingMessage = "getting " +getEntityClass().getSimpleName()+ " instances for userId " +userId;

		if(updatedAt != null){
			gettingMessage += " updated after " +updatedAt;
		}

		logger.debug(gettingMessage);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery(getUpdatedAfterQuery());
			if(updatedAt != null){
				query.setParameter(UPDATED_AT_PARAMETER, updatedAt);
			}
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

	@SuppressWarnings("unchecked")
	public List<E> findPushedAfter(Date pushedAt, Long userId) {
		String gettingMessage = "getting " +getEntityClass().getSimpleName()+ " instances for userId " +userId;

		if(pushedAt != null){
			gettingMessage += " pushed after " +pushedAt;
		}

		logger.debug(gettingMessage);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery(getPushedAfterQuery());
			if(pushedAt != null){
				query.setParameter(PUSHED_AT_PARAMETER, pushedAt);
			}
			query.setParameter(USER_ID_PARAMETER, userId);
			List<E> resultList = query.list();
			if(resultList.size() > 0){
				logger.debug("get successful");
			} else {
				logger.debug("no record found pushed after " +pushedAt);
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
