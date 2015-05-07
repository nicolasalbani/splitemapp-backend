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

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.splitemapp.commons.domain.dto.UserAvatarDTO;

/**
 * UserContactData generated by hbm2java
 */
@Entity(name = "user_avatar")
@Table(name = "user_avatar", catalog = "splitemapp", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class UserAvatar implements java.io.Serializable {

	private static final long serialVersionUID = 8927823575372766924L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "avatar_data")
	@DatabaseField(dataType = DataType.BYTE_ARRAY)
	private byte[] avatarData;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 19)
	private Date updatedAt;

	public UserAvatar() {
		this.createdAt = this.updatedAt = new Date();
	}
	
	public UserAvatar(User user, UserAvatarDTO userAvatarDTO){
		this.user = user;
		this.avatarData = userAvatarDTO.getAvatarData();
		this.createdAt = userAvatarDTO.getCreatedAt();
		this.updatedAt = userAvatarDTO.getUpdatedAt();
	}

	public UserAvatar(User user, byte[] avatarData, Date createdAt, Date updatedAt) {
		this.user = user;
		this.avatarData = avatarData;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public byte[] getAvatarData() {
		return avatarData;
	}

	public void setAvatarData(byte[] avatarData) {
		this.avatarData = avatarData;
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

}
