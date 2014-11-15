package com.splitemapp.commons.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.splitemapp.commons.domain.UserExpense;

public class UserExpenseDTO implements java.io.Serializable {

	private static final long serialVersionUID = 2204965154783892793L;

	private Long id;
	private UserDTO userDTO;
	private ProjectDTO projectDTO;
	private ExpenseCategoryDTO expenseCategoryDTO;
	private BigDecimal expense;
	private Date createdAt;
	private Date updatedAt;
	private Date expenseDate;
	private String note;
	
	public UserExpenseDTO(){}

	public UserExpenseDTO(UserExpense userExpense) {
		this.userDTO = new UserDTO(userExpense.getUser());
		this.projectDTO = new ProjectDTO(userExpense.getProject());
		this.expenseCategoryDTO = new ExpenseCategoryDTO(userExpense.getExpenseCategory());
		this.expense = userExpense.getExpense();
		this.expenseDate = userExpense.getExpenseDate();
		this.note = userExpense.getNote();
	}

	public UserExpenseDTO(UserDTO user, ProjectDTO projectDTO,
			ExpenseCategoryDTO expenseCategoryDTO, BigDecimal expense,
			Date expenseDate, String note) {
		this.userDTO = user;
		this.projectDTO = projectDTO;
		this.expenseCategoryDTO = expenseCategoryDTO;
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

	public UserDTO getUser() {
		return this.userDTO;
	}

	public void setUser(UserDTO user) {
		this.userDTO = user;
	}

	public ProjectDTO getProjectDTO() {
		return this.projectDTO;
	}

	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

	public ExpenseCategoryDTO getExpenseCategoryDTO() {
		return this.expenseCategoryDTO;
	}

	public void setExpenseCategoryDTO(ExpenseCategoryDTO expenseCategoryDTO) {
		this.expenseCategoryDTO = expenseCategoryDTO;
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
