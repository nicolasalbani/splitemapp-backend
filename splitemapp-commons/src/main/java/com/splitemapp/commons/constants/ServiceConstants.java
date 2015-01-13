package com.splitemapp.commons.constants;

public class ServiceConstants {
	// General service constants
	public static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
	
	// Service paths
	public static final String LOGIN_PATH = "/login";
	public static final String CREATE_ACCOUNT_PATH = "/create_account";
	public static final String PULL_USERS_PATH = "/pull_users";
	public static final String PULL_PROJECTS_PATH = "/pull_projects";
	public static final String PULL_GROUPS_PATH = "/pull_groups";
	public static final String PULL_USER_CONTACT_DATAS_PATH = "/pull_user_contact_datas";
	public static final String PULL_USER_EXPENSES_PATH = "/pull_user_expenses";
	public static final String PULL_USER_INVITES_PATH = "/pull_user_invites";
	public static final String PULL_USER_TO_GROUPS_PATH = "/pull_user_to_groups";
	public static final String PULL_USER_TO_PROJECTS_PATH = "/pull_user_to_projects";
	public static final String PUSH_USERS_PATH = "/push_users";
	public static final String PUSH_PROJECTS_PATH = "/push_projects";
	public static final String PUSH_GROUPS_PATH = "/push_groups";
	public static final String PUSH_USER_CONTACT_DATAS_PATH = "/push_user_contact_datas";
	public static final String PUSH_USER_EXPENSES_PATH = "/push_user_expenses";
	public static final String PUSH_USER_INVITES_PATH = "/push_user_invites";
	public static final String PUSH_USER_TO_GROUPS_PATH = "/push_user_to_groups";
	public static final String PUSH_USER_TO_PROJECTS_PATH = "/push_user_to_projects";
	
	
	// JSON ROOT names
	public static final String LOGIN_REQUEST_ROOT = "LoginRequest";
	public static final String LOGIN_RESPONSE_ROOT = "LoginResponse";
	public static final String CREATE_ACCOUNT_REQUEST_ROOT = "CreateAccountRequest";
	public static final String CREATE_ACCOUNT_RESPONSE_ROOT = "CreateAccountResponse";
	public static final String PULL_ALL_SYNC_REQUEST_ROOT = "PullAllSyncRequest";
	public static final String PULL_ALL_SYNC_RESPONSE_ROOT = "PullAllSyncResponse";
}
