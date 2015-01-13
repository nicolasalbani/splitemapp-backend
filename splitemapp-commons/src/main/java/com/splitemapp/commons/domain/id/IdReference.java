package com.splitemapp.commons.domain.id;

import java.io.Serializable;

public class IdReference implements Serializable {

	private static final long serialVersionUID = 8413507024571726735L;
	
	private String tableName;
	private String fieldName;
	
	public IdReference() {}
	
	public IdReference(String tableName, String fieldName) {
		this.tableName = tableName;
		this.fieldName = fieldName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
