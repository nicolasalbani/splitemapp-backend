package com.splitemapp.commons.domain.dto.request;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class PushRequest<E> {
	
	private String token;
	private List<E> itemList;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date lastPushSuccessAt;

	public PushRequest() {}
	
	public PushRequest(String token, List<E> itemList, Date lastPushSuccessAt) {
		this.token = token;
		this.itemList = itemList;
		this.lastPushSuccessAt = lastPushSuccessAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<E> getItemList() {
		return itemList;
	}

	public void setItemList(List<E> itemList) {
		this.itemList = itemList;
	}

	public Date getLastPushSuccessAt() {
		return lastPushSuccessAt;
	}

	public void setLastPushSuccessAt(Date lastPushSuccessAt) {
		this.lastPushSuccessAt = lastPushSuccessAt;
	}
}
