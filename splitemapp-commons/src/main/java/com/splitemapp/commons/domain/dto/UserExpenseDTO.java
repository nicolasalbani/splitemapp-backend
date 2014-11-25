package com.splitemapp.commons.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.splitemapp.commons.domain.UserExpense;

public class UserExpenseDTO implements java.io.Serializable {

	private static final long serialVersionUID = 2204965154783892793L;

	private Long id;
	private Long userId;
	private Long projectId;
	private Short expenseCategoryId;
	private BigDecimal expense;
	private Date createdAt;
	private Date updatedAt;
	private Date expenseDate;
	private String note;
	
	public UserExpenseDTO(){}

	public UserExpenseDTO(UserExpense userExpense) {
		this.userId = userExpense.getUser().getId();
		this.projectId = userExpense.getProject().getId();
		this.expenseCategoryId = userExpense.getExpenseCategory().getId();
		this.expense = userExpense.getExpense();
		this.expenseDate = userExpense.getExpenseDate();
		this.note = userExpense.getNote();
	}

	public UserExpenseDTO(Long userId, Long projectId,
			Short expenseCategoryId, BigDecimal expense,
			Date expenseDate, String note) {
		this.userId = userId;
		this.projectId = projectId;
		this.expenseCategoryId = expenseCategoryId;
		this.expense = expense;
		this.expenseDate = expenseDate;
		this.note = note;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUser(Long userId) {
		this.userId = userId;
	}

	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Short getExpenseCategoryId() {
		return this.expenseCategoryId;
	}

	public void setExpenseCategoryDTO(Short expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}

	public BigDecimal getExpense() {
		return this.expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	public Date getExpenseDate() {
		return this.expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
