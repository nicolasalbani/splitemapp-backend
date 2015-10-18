package com.splitemapp.commons.domain;

// Generated Sep 15, 2014 8:09:15 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.splitemapp.commons.constants.TableName;
import com.splitemapp.commons.domain.dto.ExpenseCategoryDTO;

/**
 * ExpenseCategory generated by hbm2java
 */
@Entity(name = TableName.EXPENSE_CATEGORY)
@Table(name = TableName.EXPENSE_CATEGORY, catalog = TableName.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = "cod"))
public class ExpenseCategory implements java.io.Serializable {

	private static final long serialVersionUID = -6784777617407039226L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Short id;
	
	@Column(name = "cod", unique = true, nullable = false, length = 64)
	private String cod;
	
	@Column(name = "title", nullable = false, length = 64)
	private String title;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "expenseCategory")
	private Set<UserExpense> userExpenses = new HashSet<UserExpense>(0);

	public ExpenseCategory() {
	}

	public ExpenseCategory(Short id, String cod, String title) {
		this.id = id;
		this.cod = cod;
		this.title = title;
	}
	
	public ExpenseCategory(ExpenseCategoryDTO expenseCategoryDTO) {
		this.id = expenseCategoryDTO.getId();
		this.cod = expenseCategoryDTO.getCod();
		this.title = expenseCategoryDTO.getTitle();
	}

	public ExpenseCategory(Short id, String cod, String title,
			Set<UserExpense> userExpenses) {
		this.id = id;
		this.cod = cod;
		this.title = title;
		this.userExpenses = userExpenses;
	}

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
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

	public Set<UserExpense> getUserExpenses() {
		return this.userExpenses;
	}

	public void setUserExpenses(Set<UserExpense> userExpenses) {
		this.userExpenses = userExpenses;
	}
	
}
