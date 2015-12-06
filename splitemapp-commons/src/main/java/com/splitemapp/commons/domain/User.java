package com.splitemapp.commons.domain;

// Generated Sep 15, 2014 8:09:15 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.j256.ormlite.field.DatabaseField;
import com.splitemapp.commons.domain.dto.UserDTO;

/**
 * User generated by hbm2java
 */
@Entity(name = "user")
@Table(name = "user", catalog = "splitemapp", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 8018834364942997890L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", nullable = false)
	private UserStatus userStatus;

	@Column(name = "username", unique = true, nullable = false, length = 64)
	private String username;

	@Column(name = "password", length = 40)
	private String password;

	@Column(name = "full_name", length = 45)
	private String fullName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login", length = 19)
	private Date lastLogin;

	@Column(name = "login_cnt", nullable = false)
	private int loginCnt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	private Date createdAt;

	@Column(name = "created_ip_address", nullable = false, length = 15)
	private String createdIpAddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false, length = 19)
	private Date updatedAt;

	@Column(name = "updated_ip_address", length = 15)
	private String updatedIpAddress;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserToProject> userToProjects = new HashSet<UserToProject>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserExpense> userExpenses = new HashSet<UserExpense>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserInvite> userInvites = new HashSet<UserInvite>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserContactData> userContactDatas = new HashSet<UserContactData>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserAvatar> userAvatars = new HashSet<UserAvatar>(0);

	public User() {
		this.createdAt = this.updatedAt = new Date();
	}
	
	public User(UserStatus userStatus, UserDTO userDTO) {
		this.id = userDTO.getId();
		this.userStatus = userStatus;
		this.username = userDTO.getUsername();
		this.password = userDTO.getPassword();
		this.fullName = userDTO.getFullName();
		this.lastLogin = userDTO.getLastLogin();
		this.loginCnt = userDTO.getLoginCnt();
		this.createdAt = userDTO.getCreatedAt();
		this.createdIpAddress = userDTO.getCreatedIpAddress();
		this.updatedAt = userDTO.getUpdatedAt();
		this.updatedIpAddress = userDTO.getUpdatedIpAddress();
	}

	public User(UserStatus userStatus, String username, int loginCnt,
			Date createdAt, String createdIpAddress, Date updatedAt, byte[] avatar) {
		this.userStatus = userStatus;
		this.username = username;
		this.loginCnt = loginCnt;
		this.createdAt = createdAt;
		this.createdIpAddress = createdIpAddress;
		this.updatedAt = updatedAt;
	}

	public User(UserStatus userStatus, String username, String password,
			String fullName, String lastName, Date lastLogin, int loginCnt,
			Date createdAt, String createdIpAddress, Date updatedAt, byte[] avatar,
			String updatedIpAddress, Set<UserToProject> userToProjects,
			Set<UserExpense> userExpenses, Set<UserInvite> userInvites,
			Set<UserContactData> userContactDatas, Set<UserAvatar> userAvatars) {
		this.userStatus = userStatus;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.lastLogin = lastLogin;
		this.loginCnt = loginCnt;
		this.createdAt = createdAt;
		this.createdIpAddress = createdIpAddress;
		this.updatedAt = updatedAt;
		this.updatedIpAddress = updatedIpAddress;
		this.userToProjects = userToProjects;
		this.userExpenses = userExpenses;
		this.userInvites = userInvites;
		this.userContactDatas = userContactDatas;
		this.userAvatars = userAvatars;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserStatus getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getLoginCnt() {
		return this.loginCnt;
	}

	public void setLoginCnt(int loginCnt) {
		this.loginCnt = loginCnt;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedIpAddress() {
		return this.createdIpAddress;
	}

	public void setCreatedIpAddress(String createdIpAddress) {
		this.createdIpAddress = createdIpAddress;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedIpAddress() {
		return this.updatedIpAddress;
	}

	public void setUpdatedIpAddress(String updatedIpAddress) {
		this.updatedIpAddress = updatedIpAddress;
	}

	public Set<UserToProject> getUserToProjects() {
		return this.userToProjects;
	}

	public void setUserToProjects(Set<UserToProject> userToProjects) {
		this.userToProjects = userToProjects;
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

	public Set<UserContactData> getUserContactDatas() {
		return this.userContactDatas;
	}

	public void setUserContactDatas(Set<UserContactData> userContactDatas) {
		this.userContactDatas = userContactDatas;
	}

	public Set<UserAvatar> getUserAvatars() {
		return this.userAvatars;
	}

	public void setUserAvatars(Set<UserAvatar> userAvatars) {
		this.userAvatars = userAvatars;
	}

	@Override
	public int hashCode() {
		return this.id.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof User){
			return this.getId().equals(((User)obj).getId());
		}
		return false;
	}
}
