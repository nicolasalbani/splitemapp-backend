package com.splitemapp.commons.domain.dto.response;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class PullResponse<E> extends ServiceResponse implements Serializable {
	
	private static final long serialVersionUID = 7262133901637892203L;
	
	private Set<E> itemSet;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date pulledAt;
	
	public PullResponse() {
		itemSet = new HashSet<E>();
		pulledAt = new Date();
	}
	
	public Set<E> getItemSet() {
		return itemSet;
	}

	public void setItemSet(Set<E> itemSet) {
		this.itemSet = itemSet;
	}

	public Date getPulledAt() {
		return pulledAt;
	}

	public void setPulledAt(Date pulledAt) {
		this.pulledAt = pulledAt;
	}
}
