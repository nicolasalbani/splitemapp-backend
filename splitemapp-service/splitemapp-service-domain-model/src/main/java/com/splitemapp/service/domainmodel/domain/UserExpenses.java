package com.splitemapp.service.domainmodel.domain;

// Generated Sep 15, 2014 8:09:15 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserExpenses generated by hbm2java
 */
@Entity
@Table(name = "user_expenses", catalog = "splitshares")
public class UserExpenses implements java.io.Serializable {

	private static final long serialVersionUID = 2204965154783892793L;
	private Long id;
	private User user;
	private Project project;
	private ExpenseCategory expenseCategory;
	private BigDecimal expense;
	private Date expenseDate;
	private String note;

	public UserExpenses() {
	}

	public UserExpenses(User user, Project project,
			ExpenseCategory expenseCategory, BigDecimal expense) {
		this.user = user;
		this.project = project;
		this.expenseCategory = expenseCategory;
		this.expense = expense;
	}

	public UserExpenses(User user, Project project,
			ExpenseCategory expenseCategory, BigDecimal expense,
			Date expenseDate, String note) {
		this.user = user;
		this.project = project;
		this.expenseCategory = expenseCategory;
		this.expense = expense;
		this.expenseDate = expenseDate;
		this.note = note;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	public ExpenseCategory getExpenseCategory() {
		return this.expenseCategory;
	}

	public void setExpenseCategory(ExpenseCategory expenseCategory) {
		this.expenseCategory = expenseCategory;
	}

	@Column(name = "expense", nullable = false, precision = 16, scale = 6)
	public BigDecimal getExpense() {
		return this.expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expense_date", length = 19)
	public Date getExpenseDate() {
		return this.expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	@Column(name = "note", length = 65535)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}