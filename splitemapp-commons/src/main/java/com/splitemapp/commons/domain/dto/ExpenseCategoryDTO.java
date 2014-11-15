package com.splitemapp.commons.domain.dto;

import com.splitemapp.commons.domain.ExpenseCategory;

public class ExpenseCategoryDTO implements java.io.Serializable {

	private static final long serialVersionUID = -6784777617407039226L;
	
	private short id;
	private String cod;
	private String title;

	public ExpenseCategoryDTO(){}
	
	public ExpenseCategoryDTO(ExpenseCategory expenseCategory) {
		this.id = expenseCategory.getId();
		this.cod = expenseCategory.getCod();
		this.title = expenseCategory.getTitle();
	}

	public ExpenseCategoryDTO(short id, String cod, String title) {
		this.id = id;
		this.cod = cod;
		this.title = title;
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
