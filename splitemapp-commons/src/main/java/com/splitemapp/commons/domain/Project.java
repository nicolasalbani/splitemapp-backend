package com.splitemapp.commons.domain;

// Generated Sep 15, 2014 8:09:15 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.j256.ormlite.field.DatabaseField;
import com.splitemapp.commons.domain.dto.ProjectDTO;

/**
 * Project generated by hbm2java
 */
@Entity(name = "project")
@Table(name = "project", catalog = "splitemapp")
public class Project implements java.io.Serializable {

	private static final long serialVersionUID = -1186403556721128678L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", nullable = false)
	private ProjectType projectType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", nullable = false)
	private ProjectStatus projectStatus;
	
	@Column(name = "title", length = 128)
	private String title;
	
	@Column(name = "budget", precision = 16, scale = 6)
	private BigDecimal budget;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false, length = 19)
	private Date updatedAt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<UserExpense> userExpenses = new HashSet<UserExpense>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<UserInvite> userInvites = new HashSet<UserInvite>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<UserToProject> userToProjects = new HashSet<UserToProject>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<ProjectCoverImage> projectCoverImages = new HashSet<ProjectCoverImage>(0);

	public Project() {
		this.createdAt = this.updatedAt = new Date();
	}

	public Project(ProjectType projectType, ProjectStatus projectStatus,
			Date createdAt, Date updatedAt) {
		this.projectType = projectType;
		this.projectStatus = projectStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Project(ProjectType projectType, ProjectStatus projectStatus, ProjectDTO projectDTO) {
		this.id = projectDTO.getId();
		this.projectType = projectType;
		this.projectStatus = projectStatus;
		this.title = projectDTO.getTitle();
		this.budget = projectDTO.getBudget();
		this.createdAt = projectDTO.getCreatedAt();
		this.updatedAt = projectDTO.getUpdatedAt();
	}

	public Project(ProjectType projectType, ProjectStatus projectStatus,
			String title, String imgCover, BigDecimal budget, Date createdAt,
			Date updatedAt, Set<UserExpense> userExpenses,
			Set<UserInvite> userInvites, Set<UserToProject> userToProjects, 
			Set<ProjectCoverImage> projectCoverImages) {
		this.projectType = projectType;
		this.projectStatus = projectStatus;
		this.title = title;
		this.budget = budget;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userExpenses = userExpenses;
		this.userInvites = userInvites;
		this.userToProjects = userToProjects;
		this.projectCoverImages = projectCoverImages;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectType getProjectType() {
		return this.projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public ProjectStatus getProjectStatus() {
		return this.projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getBudget() {
		return this.budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<UserExpense> getUserExpenses() {
		return this.userExpenses;
	}

	public void setUserExpenses(Set<UserExpense> userExpenses) {
		this.userExpenses = userExpenses;
	}

	public Set<UserInvite> getUserInvites() {
		return this.userInvites;
	}

	public void setUserInvites(Set<UserInvite> userInvites) {
		this.userInvites = userInvites;
	}

	public Set<UserToProject> getUserToProjects() {
		return this.userToProjects;
	}

	public void setUserToProjects(Set<UserToProject> userToProjects) {
		this.userToProjects = userToProjects;
	}

	public Set<ProjectCoverImage> getProjectCoverImages() {
		return projectCoverImages;
	}

	public void setProjectCoverImages(Set<ProjectCoverImage> projectCoverImages) {
		this.projectCoverImages = projectCoverImages;
	}
	
	@Override
	public int hashCode() {
		return this.id.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Project){
			return this.getId().equals(((Project)obj).getId());
		}
		return false;
	}
}
