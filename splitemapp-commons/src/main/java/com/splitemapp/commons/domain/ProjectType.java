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

/**
 * ProjectType generated by hbm2java
 */
@Entity(name = "project_type")
@Table(name = "project_type", catalog = "splitemapp", uniqueConstraints = @UniqueConstraint(columnNames = "cod"))
public class ProjectType implements java.io.Serializable {

	private static final long serialVersionUID = 8129816440139286101L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private short id;
	
	@Column(name = "cod", unique = true, nullable = false, length = 64)
	private String cod;
	
	@Column(name = "title", nullable = false, length = 64)
	private String title;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectType")
	private Set<Project> projects = new HashSet<Project>(0);

	public ProjectType() {
	}

	public ProjectType(short id, String cod, String title) {
		this.id = id;
		this.cod = cod;
		this.title = title;
	}

	public ProjectType(short id, String cod, String title, Set<Project> projects) {
		this.id = id;
		this.cod = cod;
		this.title = title;
		this.projects = projects;
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

	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

}
