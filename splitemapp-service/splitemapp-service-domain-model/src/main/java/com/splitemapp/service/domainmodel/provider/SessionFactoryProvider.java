package com.splitemapp.service.domainmodel.provider;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.splitemapp.domainmodel.domain.ExpenseCategory;
import com.splitemapp.domainmodel.domain.Group;
import com.splitemapp.domainmodel.domain.GroupStatus;
import com.splitemapp.domainmodel.domain.InviteStatus;
import com.splitemapp.domainmodel.domain.Project;
import com.splitemapp.domainmodel.domain.ProjectStatus;
import com.splitemapp.domainmodel.domain.ProjectType;
import com.splitemapp.domainmodel.domain.User;
import com.splitemapp.domainmodel.domain.UserContactData;
import com.splitemapp.domainmodel.domain.UserExpenses;
import com.splitemapp.domainmodel.domain.UserInvite;
import com.splitemapp.domainmodel.domain.UserStatus;
import com.splitemapp.domainmodel.domain.UserToGroup;
import com.splitemapp.domainmodel.domain.UserToGroupStatus;
import com.splitemapp.domainmodel.domain.UserToProject;
import com.splitemapp.domainmodel.domain.UserToProjectStatus;

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
		.addAnnotatedClass(UserStatus.class)
		.addAnnotatedClass(UserContactData.class)
		.addAnnotatedClass(UserExpenses.class)
		.addAnnotatedClass(ExpenseCategory.class)
		.addAnnotatedClass(Project.class)
		.addAnnotatedClass(ProjectStatus.class)
		.addAnnotatedClass(ProjectType.class)
		.addAnnotatedClass(UserInvite.class)
		.addAnnotatedClass(InviteStatus.class)
		.addAnnotatedClass(UserToGroup.class)
		.addAnnotatedClass(UserToProject.class)
		.addAnnotatedClass(UserToProjectStatus.class)
		.addAnnotatedClass(UserToGroupStatus.class)
		.addAnnotatedClass(Group.class)
		.addAnnotatedClass(GroupStatus.class);
	}
}