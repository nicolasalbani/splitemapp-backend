package com.splitemapp.commons.constants;

public class ServiceConstants {
	// General service GET success response
	public static final String GET_SUCCESS = "Your GET request was successfull. Service is alive!";
	
	// GCM Constants
	public static final String GCM_DEFAULT_SENDER_ID = "66220540369";
	public static final String GCM_SERVER_URL = "https://gcm-http.googleapis.com/gcm/send";
	public static final String API_KEY = "AIzaSyCREdjyI-IBHiaX07Tbld7ZOqTZxw4p51I";
	
	// General service constants
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
	
	// Service paths
	public static final String LOGIN_PATH = "/login";
	public static final String CREATE_ACCOUNT_PATH = "/create_account";
	public static final String PULL_USERS_PATH = "/pull_users";
	public static final String PULL_USER_AVATARS_PATH = "/pull_user_avatars";
	public static final String PULL_PROJECTS_PATH = "/pull_projects";
	public static final String PULL_PROJECT_COVER_IMAGES_PATH = "/pull_project_cover_images";
	public static final String PULL_USER_CONTACT_DATAS_PATH = "/pull_user_contact_datas";
	public static final String PULL_USER_EXPENSES_PATH = "/pull_user_expenses";
	public static final String PULL_USER_INVITES_PATH = "/pull_user_invites";
	public static final String PULL_USER_TO_PROJECTS_PATH = "/pull_user_to_projects";
	public static final String PUSH_USERS_PATH = "/push_users";
	public static final String PUSH_USER_SESSIONS_PATH = "/push_user_sessions";
	public static final String PUSH_PROJECTS_PATH = "/push_projects";
	public static final String PUSH_PROJECT_COVER_IMAGES_PATH = "/push_project_cover_images";
	public static final String PUSH_USER_AVATARS_PATH = "/push_user_avatars";
	public static final String PUSH_USER_CONTACT_DATAS_PATH = "/push_user_contact_datas";
	public static final String PUSH_USER_EXPENSES_PATH = "/push_user_expenses";
	public static final String PUSH_USER_INVITES_PATH = "/push_user_invites";
	public static final String PUSH_USER_TO_PROJECTS_PATH = "/push_user_to_projects";
	public static final String SYNCHRONIZE_CONTACTS_PATH = "/synchronize_contacts";
}
