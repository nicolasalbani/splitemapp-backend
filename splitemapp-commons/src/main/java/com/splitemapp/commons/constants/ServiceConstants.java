package com.splitemapp.commons.constants;

public class ServiceConstants {
	/**
	 *  General service GET success response
	 */
	public static final String GET_SUCCESS = "Your GET request was successfull. Service is alive!";

	/**
	 * Back-end server information
	 */
	public static final String BACKEND_HOST="backend.splitemapp.com";
	public static final String BACKEND_PORT="8080";
	public static final String BACKEND_PATH="splitemapp-backend-rest";
	
	/**
	 * Mail server information
	 */
	public static final String SMTP_HOST = "smtp.gmail.com";
	public static final String SMTP_PORT = "587";
	public static final String MAIL_DOMAIN = "splitemapp.com";
	public static final String INFO_SERVICE_ADDRESS = "info@splitemapp.com";
	public static final String INFO_SERVICE_PASS = "019713skull";

	/**
	 * GCM Constants
	 */
	public static final String GCM_DEFAULT_SENDER_ID = "66220540369";
	public static final String GCM_SERVER_URL = "https://gcm-http.googleapis.com/gcm/send";
	public static final String API_KEY = "AIzaSyCREdjyI-IBHiaX07Tbld7ZOqTZxw4p51I";

	/**
	 * Broadcast Constants
	 */
	public static final String REST_MESSAGE = "com.splitemapp.android.service.REST_MESSAGE";
	public static final String UI_MESSAGE = "com.splitemapp.android.service.UI_MESSAGE";
	public static final String CONTENT_ACTION = "com.splitemapp.android.service.CONTENT_ACTION";
	public static final String CONTENT_RESPONSE = "com.splitemapp.android.service.CONTENT_RESPONSE";
	public static final String PROJECT_ID = "com.splitemapp.android.service.PROJECT_ID";

	/**
	 * General Service Constants
	 */
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

	/**
	 * Service Error Messages
	 */
	public static final String ERROR_MESSAGE_ACCOUNT_EXISTS = "ERROR_MESSAGE_ACCOUNT_EXISTS";
	public static final String ERROR_MESSAGE_LOGIN_FAILED = "ERROR_MESSAGE_LOGIN_FAILED";
	public static final String ERROR_MESSAGE_ACCOUNT_NOT_FOUND = "ERROR_MESSAGE_ACCOUNT_NOT_FOUND";
	public static final String ERROR_MESSAGE_SERVER_ERROR = "ERROR_MESSAGE_SERVER_ERROR";
	public static final String ERROR_MESSAGE_NETWORK_ERROR = "ERROR_MESSAGE_NETWORK_ERROR";

	/**
	 * Service Paths
	 */
	public static final String LOGIN_PATH = "/login";
	public static final String LOGOUT_PATH = "/logout";
	public static final String CHECK_ACCOUNT_PATH = "/check_account";
	public static final String CHANGE_PASSWORD_PATH = "/change_password";
	public static final String PASSWORD_RESET_PATH = "/password_reset";
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
	public static final String QUESTIONS_PATH = "/questions";
	public static final String INVITE_PATH = "/invite";
}
