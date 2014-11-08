package com.splitemapp.service.backendrest.endpoint;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.splitemapp.service.domainmodel.domain.User;
import com.splitemapp.service.backendrest.dao.DomainDAO;

public class UserEndpoint extends DomainDAO<User> {

	private static Logger logger = Logger.getLogger(UserEndpoint.class);

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@SuppressWarnings("unchecked")
	public User findByUsername(String userName) {
		logger.debug("getting " +getEntityClass().getSimpleName()+ " instance with username: " + userName);

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("FROM " +getEntityClass().getSimpleName()+ " E WHERE E.username = :user_name");
			query.setParameter("user_name", userName);
			List<User> resultList = query.list();
			if(resultList.size() == 1){
				logger.debug("get successful");
				return resultList.get(0);
			} else if (resultList.size() > 1){
				throw new RuntimeException("more than one record with the same username!");
			} else {
				logger.debug("no record found for username: " +userName);
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
