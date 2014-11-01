package com.splitemapp.service.domainmodel.domain;

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
 * UserToGroupStatus generated by hbm2java
 */
@Entity
@Table(name = "user_to_group_status", catalog = "splitshares", uniqueConstraints = @UniqueConstraint(columnNames = "cod"))
public class UserToGroupStatus implements java.io.Serializable {

	private static final long serialVersionUID = -1355846727766418533L;
	private short id;
	private String cod;
	private String title;
	private Set<UserToGroup> userToGroups = new HashSet<UserToGroup>(0);

	public UserToGroupStatus() {
	}

	public UserToGroupStatus(short id, String cod, String title) {
		this.id = id;
		this.cod = cod;
		this.title = title;
	}

	public UserToGroupStatus(short id, String cod, String title,
			Set<UserToGroup> userToGroups) {
		this.id = id;
		this.cod = cod;
		this.title = title;
		this.userToGroups = userToGroups;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	@Column(name = "cod", unique = true, nullable = false, length = 64)
	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	@Column(name = "title", nullable = false, length = 64)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userToGroupStatus")
	public Set<UserToGroup> getUserToGroups() {
		return this.userToGroups;
	}

	public void setUserToGroups(Set<UserToGroup> userToGroups) {
		this.userToGroups = userToGroups;
	}

}