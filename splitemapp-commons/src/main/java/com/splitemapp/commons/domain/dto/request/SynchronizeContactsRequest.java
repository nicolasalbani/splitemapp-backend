package com.splitemapp.commons.domain.dto.request;

import java.util.List;

public class SynchronizeContactsRequest {

	private List<String> contactsEmailAddressList;
	private String token;

	/**
	 * Required by FasterXML.
	 */
	public SynchronizeContactsRequest() {}

	public SynchronizeContactsRequest(List<String> contactsEmailAddressList, String token) {
		this.contactsEmailAddressList = contactsEmailAddressList;
		this.token = token;
	}

	public List<String> getContactsEmailAddressList() {
		return contactsEmailAddressList;
	}

	public void setContactsEmailAddressList(List<String> contactsEmailAddressList) {
		this.contactsEmailAddressList = contactsEmailAddressList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
