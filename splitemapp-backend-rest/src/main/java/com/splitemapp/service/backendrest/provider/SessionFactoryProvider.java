package com.splitemapp.service.backendrest.provider;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.splitemapp.commons.domain.ExpenseCategory;
import com.splitemapp.commons.domain.InviteStatus;
import com.splitemapp.commons.domain.Project;
import com.splitemapp.commons.domain.ProjectStatus;
import com.splitemapp.commons.domain.ProjectType;
import com.splitemapp.commons.domain.User;
import com.splitemapp.commons.domain.UserAvatar;
import com.splitemapp.commons.domain.UserContactData;
import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.commons.domain.UserInvite;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.UserStatus;
import com.splitemapp.commons.domain.UserToProject;
import com.splitemapp.commons.domain.UserToProjectStatus;

public class SessionFactoryProvider {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	/**
	 * 
	 * @return	SessionFactory static instance ready to be used
	 */
	public static SessionFactory getSessionFactory() {
		
		if(sessionFactory==null){
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			addAnnotatedClasses(configuration);
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}

		return sessionFactory;
	}

	//We add the entire domain model as annotated classes to the configuration
	private static void addAnnotatedClasses(Configuration configuration){
		configuration.addAnnotatedClass(User.class)
		.addAnnotatedClass(UserAvatar.class)
		.addAnnotatedClass(UserStatus.class)
		.addAnnotatedClass(UserContactData.class)
		.addAnnotatedClass(UserExpense.class)
		.addAnnotatedClass(ExpenseCategory.class)
		.addAnnotatedClass(Project.class)
		.addAnnotatedClass(ProjectStatus.class)
		.addAnnotatedClass(ProjectType.class)
		.addAnnotatedClass(UserInvite.class)
		.addAnnotatedClass(InviteStatus.class)
		.addAnnotatedClass(UserToProject.class)
		.addAnnotatedClass(UserToProjectStatus.class)
		.addAnnotatedClass(UserSession.class);
	}
}