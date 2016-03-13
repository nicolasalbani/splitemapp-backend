package com.splitemapp.commons.domain;

// Generated Sep 15, 2014 8:09:15 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.j256.ormlite.field.DatabaseField;
import com.splitemapp.commons.domain.dto.UserToProjectDTO;

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
	@DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_to_project_status_id", nullable = false)
	private UserToProjectStatus userToProjectStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false, length = 19)
	private Date updatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pushed_at", length = 19)
	private Date pushedAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updated_by", nullable = false)
	private User updatedBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pushed_by")
	private User pushedBy;

	@Column(name = "expenses_share", nullable = false, precision = 3)
	private Float expensesShare;

	public UserToProject() {
		this.createdAt = this.updatedAt = new Date();
	}
	
	public UserToProject(User user, Project project,
			UserToProjectStatus userToProjectStatus, 
			User updatedBy, User pushedBy, UserToProjectDTO userToProjectDTO) {
		this.id = userToProjectDTO.getId();
		this.user = user;
		this.project = project;
		this.userToProjectStatus = userToProjectStatus;
		this.createdAt = userToProjectDTO.getCreatedAt();
		this.updatedAt = userToProjectDTO.getUpdatedAt();
		this.updatedBy = updatedBy;
		this.pushedAt = userToProjectDTO.getPushedAt();
		this.pushedBy = pushedBy;
		this.expensesShare = userToProjectDTO.getExpensesShare();
	}

	public UserToProject(User user, Project project,
			UserToProjectStatus userToProjectStatus, Float expensesShare) {
		this.user = user;
		this.project = project;
		this.userToProjectStatus = userToProjectStatus;
		this.expensesShare = expensesShare;
	}
	
	public UserToProject(Long id, User user, Project project,
			UserToProjectStatus userToProjectStatus, Date createdAt,
			Date updatedAt, Date pushedAt, Float expensesShare) {
		this.id = id;
		this.user = user;
		this.project = project;
		this.userToProjectStatus = userToProjectStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.pushedAt = pushedAt;
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

	public Float getExpensesShare() {
		return this.expensesShare;
	}

	public void setExpensesShare(Float expensesShare) {
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

	public Date getPushedAt() {
		return pushedAt;
	}

	public void setPushedAt(Date pushedAt) {
		this.pushedAt = pushedAt;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public User getPushedBy() {
		return pushedBy;
	}

	public void setPushedBy(User pushedBy) {
		this.pushedBy = pushedBy;
	}
	
}
