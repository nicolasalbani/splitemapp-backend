package com.splitemapp.commons.domain.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.splitemapp.commons.domain.id.IdUpdate;

public class PushResponse<E extends Number> extends ServiceResponse implements Serializable {

	private static final long serialVersionUID = 6974345665547687236L;
	
	private List<IdUpdate<E>> idUpdateList;
	
	public PushResponse() {
		super();
		idUpdateList = new ArrayList<IdUpdate<E>>();
	}

	public PushResponse(Boolean success, List<IdUpdate<E>> idUpdateList) {
		super(success);
		this.idUpdateList = idUpdateList;
	}

	public List<IdUpdate<E>> getIdUpdateList() {
		return idUpdateList;
	}

	public void setIdUpdateList(List<IdUpdate<E>> idUpdateList) {
		this.idUpdateList = idUpdateList;
	}
	
}
