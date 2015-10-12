package com.splitemapp.service.backendrest.endpoint;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.splitemapp.commons.domain.User;
import com.splitemapp.service.backendrest.dao.UpdateableDomainDAO;

public class UserEndpoint extends UpdateableDomainDAO<User,Long> {
	
	private Logger logger = Logger.getLogger(UserEndpoint.class);

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public String getCreatedAfterQuery() {
		return "SELECT DISTINCT U FROM user U LEFT JOIN U.userToProjects UTP WHERE UTP.user.id = :" +USER_ID_PARAMETER + " AND U.createdAt > :" +CREATED_AT_PARAMETER;
	}

	@Override
	public String getUpdatedAfterQuery() {
		return "SELECT DISTINCT U FROM user U LEFT JOIN U.userToProjects UTP WHERE UTP.user.id = :" +USER_ID_PARAMETER + " AND U.updatedAt > :" +UPDATED_AT_PARAMETER;
	}

	@SuppressWarnings("unchecked")
	public List<User> findLinkedByProjectUserIds(Date updatedAt, Long userId) {
		logger.debug("getting list of users updated after " +updatedAt+ " and linked through a project to userId " +userId);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("SELECT U FROM user U WHERE U.id IN (SELECT UTP.user.id FROM user_to_project UTP WHERE UTP.id IN (SELECT UTP.id FROM user_to_project UTP WHERE UTP.user.id  = :" +USER_ID_PARAMETER + "))");
			query.setParameter(USER_ID_PARAMETER, userId);
			List<User> resultList = query.list();
			if(resultList.size() > 0){
				logger.debug("get successful");
			} else {
				logger.debug("no users found linked through a project to userId " +userId);
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
