package com.splitemapp.commons.constants;

public class TableField {
	/**
	 * 'user' table
	 */
	public static final String USER_ID = "id";
	public static final String USER_USERNAME = "username";
	
	/**
	 * 'user_avatar'
	 */
	public static final String USER_AVATAR_USER_ID = "user_id";
	
	/**
	 * 'user_invite'
	 */
	public static final String USER_INVITE_ID = "id";
	public static final String USER_INVITE_USER_ID = "user_id";
	public static final String USER_INVITE_PROJECT_ID = "project_id";
	
	/**
	 * 'user_expense' table
	 */
	public static final String USER_EXPENSE_ID = "id";
	public static final String USER_EXPENSE_USER_ID = "user_id";
	public static final String USER_EXPENSE_PROJECT_ID = "project_id";
	
	/**
	 * 'user_session' table
	 */
	public static final String USER_SESSION_TOKEN = "token";
	
	/**
	 * Generic alter table
	 */
	public static final String ALTER_TABLE_COD = "cod";
	
	/**
	 * 'contact_data' table
	 */
	public static final String USER_CONTACT_DATA_ID = "id";
	public static final String USER_CONTACT_DATA_USER_ID = "user_id";
	public static final String USER_CONTACT_DATA_CONTACT_DATA = "contact_data";
	
	/**
	 * 'project' table
	 */
	public static final String PROJECT_ID = "id";
	
	/**
	 * 'project_cover_image' table
	 */
	public static final String PROJECT_COVER_IMAGE_ID = "id";
	public static final String PROJECT_COVER_IMAGE_PROJECT_ID = "project_id";
	
	/**
	 * 'user_to_project' table
	 */
	public static final String USER_TO_PROJECT_ID = "id";
	public static final String USER_TO_PROJECT_PROJECT_ID = "project_id";
	public static final String USER_TO_PROJECT_USER_ID = "user_id";
	public static final String USER_TO_PROJECT_COD = "cod";
	
	/**
	 * 'project_type' table
	 */
	public static final String PROJECT_TYPE_COD = "cod";
	
	/**
	 * 'expense_status' table
	 */
	public static final String EXPENSE_STATUS_COD = "cod";
	
	/**
	 * 'sync_status' table
	 */
	public static final String SYNC_STATUS_TABLE_NAME = "table_name";
}
