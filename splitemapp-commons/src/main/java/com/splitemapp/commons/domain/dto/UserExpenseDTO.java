package com.splitemapp.commons.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.splitemapp.commons.domain.UserExpense;
import com.splitemapp.commons.domain.dto.serializer.CustomDateDeserializer;
import com.splitemapp.commons.domain.dto.serializer.CustomDateSerializer;

public class UserExpenseDTO implements java.io.Serializable {

	private static final long serialVersionUID = 2204965154783892793L;

	private Long id;
	private Long userId;
	private Long projectId;
	private Short expenseCategoryId;
	private Short expenseStatusId;
	private BigDecimal expense;
	private String note;
	private Long updatedBy;
	private Long pushedBy;

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getPushedBy() {
		return pushedBy;
	}

	public void setPushedBy(Long pushedBy) {
		this.pushedBy = pushedBy;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setExpenseCategoryId(Short expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}
	
	public void setExpenseStatusId(Short expenseStatusId) {
		this.expenseStatusId = expenseStatusId;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date createdAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date updatedAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date pushedAt;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date expenseDate;
	
	public UserExpenseDTO(){}

	public UserExpenseDTO(UserExpense userExpense) {
		this.id = userExpense.getId();
		this.userId = userExpense.getUser().getId();
		this.projectId = userExpense.getProject().getId();
		this.expenseCategoryId = userExpense.getExpenseCategory().getId();
		this.expenseStatusId = userExpense.getExpenseStatus().getId();
		this.expense = userExpense.getExpense();
		this.expenseDate = userExpense.getExpenseDate();
		this.note = userExpense.getNote();
		this.createdAt = userExpense.getCreatedAt();
		this.updatedAt = userExpense.getUpdatedAt();
		this.updatedBy = userExpense.getUpdatedBy().getId();
		this.pushedAt = userExpense.getPushedAt();
		if(userExpense.getPushedBy() != null){
			this.pushedBy = userExpense.getPushedBy().getId();
		}
	}

	public UserExpenseDTO(Long userId, Long projectId,
			Short expenseCategoryId, Short expenseStatusId, BigDecimal expense,
			Date expenseDate, String note) {
		this.userId = userId;
		this.projectId = projectId;
		this.expenseCategoryId = expenseCategoryId;
		this.expenseStatusId = expenseStatusId;
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
	
	public Short getExpenseStatusId() {
		return this.expenseStatusId;
	}

	public void setExpenseStatusDTO(Short expenseStatusId) {
		this.expenseStatusId = expenseStatusId;
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

	public Date getPushedAt() {
		return pushedAt;
	}

	public void setPushedAt(Date pushedAt) {
		this.pushedAt = pushedAt;
	}

}
