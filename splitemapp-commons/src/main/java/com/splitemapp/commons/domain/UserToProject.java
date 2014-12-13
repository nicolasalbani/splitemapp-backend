package com.splitemapp.commons.domain;

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
import javax.persistence.UniqueConstraint;

/**
 * UserToProject generated by hbm2java
 */
@Entity(name = "user_to_project")
@Table(name = "user_to_project", catalog = "splitemapp", uniqueConstraints = @UniqueConstraint(columnNames = {
		"user_id", "project_id" }))
public class UserToProject implements java.io.Serializable {

	private static final long serialVersionUID = 7814066964126398718L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_status_id", nullable = false)
	private UserToProjectStatus userToProjectStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false, length = 19)
	private Date updatedAt;

	@Column(name = "expenses_share", nullable = false, precision = 3)
	private BigDecimal expensesShare;

	public UserToProject() {
	}

	public UserToProject(User user, Project project,
			UserToProjectStatus userToProjectStatus, BigDecimal expensesShare) {
		this.user = user;
		this.project = project;
		this.userToProjectStatus = userToProjectStatus;
		this.expensesShare = expensesShare;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public UserToProjectStatus getUserToProjectStatus() {
		return this.userToProjectStatus;
	}

	public void setUserToProjectStatus(UserToProjectStatus userToProjectStatus) {
		this.userToProjectStatus = userToProjectStatus;
	}

	public BigDecimal getExpensesShare() {
		return this.expensesShare;
	}

	public void setExpensesShare(BigDecimal expensesShare) {
		this.expensesShare = expensesShare;
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
