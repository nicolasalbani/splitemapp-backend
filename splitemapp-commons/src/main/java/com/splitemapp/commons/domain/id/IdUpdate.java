package com.splitemapp.commons.domain.id;

import java.io.Serializable;

public class IdUpdate<E extends Number> implements Serializable {

	private static final long serialVersionUID = -8132542935396403791L;
	
	private E oldId;
	private E newId;
	
	public IdUpdate() {}
	
	public IdUpdate(E oldId, E newId) {
		this.oldId = oldId;
		this.newId = newId;
	}

	public E getOldId() {
		return oldId;
	}

	public void setOldId(E oldId) {
		this.oldId = oldId;
	}

	public E getNewId() {
		return newId;
	}

	public void setNewId(E newId) {
		this.newId = newId;
	}
}
