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
import com.splitemapp.commons.domain.dto.UserStatusDTO;

/**
 * UserStatus generated by hbm2java
 */
@Entity(name = TableName.USER_STATUS)
@Table(name = TableName.USER_STATUS, catalog = "splitemapp", uniqueConstraints = @UniqueConstraint(columnNames = "cod"))
public class UserStatus implements java.io.Serializable {

	private static final long serialVersionUID = 4184361611600678462L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Short id;

	@Column(name = "cod", unique = true, nullable = false, length = 64)
	private String cod;

	@Column(name = "title", nullable = false, length = 64)
	private String title;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userStatus")
	private Set<User> users = new HashSet<User>(0);

	public UserStatus() {
	}
	
	public UserStatus(UserStatusDTO userStatusDTO) {
		this.id = userStatusDTO.getId();
		this.cod = userStatusDTO.getCod();
		this.title = userStatusDTO.getTitle();
	}

	public UserStatus(Short id, String cod, String title) {
		this.id = id;
		this.cod = cod;
		this.title = title;
	}

	public UserStatus(Short id, String cod, String title, Set<User> users) {
		this.id = id;
		this.cod = cod;
		this.title = title;
		this.users = users;
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

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
