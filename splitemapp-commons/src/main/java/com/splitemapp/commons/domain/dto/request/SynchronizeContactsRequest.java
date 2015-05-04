package com.splitemapp.commons.domain.dto.request;

import java.util.List;

public class SynchronizeContactsRequest {

	private List<String> contactsEmailAddressList;

	/**
	 * Required by FasterXML.
	 */
	public SynchronizeContactsRequest() {}

	public SynchronizeContactsRequest(List<String> contactsEmailAddressList) {
		this.contactsEmailAddressList = contactsEmailAddressList;
	}

	public List<String> getContactsEmailAddressList() {
		return contactsEmailAddressList;
	}

	public void setContactsEmailAddressList(List<String> contactsEmailAddressList) {
		this.contactsEmailAddressList = contactsEmailAddressList;
	}
}
