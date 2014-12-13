package com.splitemapp.commons.constants;

public class ServiceConstants {
	// General service constants
	public static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
	
	// Service paths
	public static final String LOGIN_PATH = "/login";
	public static final String CREATE_ACCOUNT_PATH = "/create_account";
	public static final String PULL_ALL_SYNC_PATH = "/pull_all_sync";
	
	// JSON ROOT names
	public static final String LOGIN_REQUEST_ROOT = "LoginRequest";
	public static final String LOGIN_RESPONSE_ROOT = "LoginResponse";
	public static final String CREATE_ACCOUNT_REQUEST_ROOT = "CreateAccountRequest";
	public static final String CREATE_ACCOUNT_RESPONSE_ROOT = "CreateAccountResponse";
	public static final String PULL_ALL_SYNC_REQUEST_ROOT = "PullAllSyncRequest";
	public static final String PULL_ALL_SYNC_RESPONSE_ROOT = "PullAllSyncResponse";
}
