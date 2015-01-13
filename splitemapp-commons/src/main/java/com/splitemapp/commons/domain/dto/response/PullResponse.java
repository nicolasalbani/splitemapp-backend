package com.splitemapp.commons.domain.dto.response;

import java.io.Serializable;
import java.util.Set;

public class PullResponse<E> extends ServiceResponse implements Serializable {
	
	private static final long serialVersionUID = 7262133901637892203L;
	
	private Set<E> itemSet;

	public PullResponse() {}
	
	public PullResponse(Boolean success, Set<E> itemSet) {
		super(success);
		this.itemSet = itemSet;
	}

	public Set<E> getItemSet() {
		return itemSet;
	}

	public void setItemSet(Set<E> itemSet) {
		this.itemSet = itemSet;
	}
	
}
