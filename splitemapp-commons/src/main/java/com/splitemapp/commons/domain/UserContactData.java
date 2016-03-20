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
import com.splitemapp.commons.domain.dto.UserContactDataDTO;

/**
 * UserContactData generated by hbm2java
 */
@Entity(name = "user_contact_data")
@Table(name = "user_contact_data", catalog = "splitemapp", uniqueConstraints = @UniqueConstraint(columnNames = {
		"user_id", "contact_data" }))
public class UserContactData implements java.io.Serializable,PushableEntity {

	private static final long serialVersionUID = 8927823575372766924L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "contact_data", length = 64)
	private String contactData;

	@Column(name = "verified", nullable = false)
	private boolean verified;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "verified_at", length = 19)
	private Date verifiedAt;

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

	public UserContactData() {
		this.createdAt = this.updatedAt = new Date();
	}
	
	public UserContactData(User user, User updatedBy, User pushedBy, UserContactDataDTO userContactDataDTO){
		this.user = user;
		this.id = userContactDataDTO.getId();
		this.contactData = userContactDataDTO.getContactData();
		this.verified = userContactDataDTO.isVerified();
		this.verifiedAt = userContactDataDTO.getVerifiedAt();
		this.createdAt = userContactDataDTO.getCreatedAt();
		this.updatedAt = userContactDataDTO.getUpdatedAt();
		this.updatedBy = updatedBy;
		this.pushedAt = userContactDataDTO.getPushedAt();
		this.pushedBy = pushedBy;
	}

	public UserContactData(User user, boolean verified, Date createdAt) {
		this.user = user;
		this.verified = verified;
		this.createdAt = createdAt;
	}

	public UserContactData(User user, String contactData, boolean verified,
			Date verifiedAt, Date createdAt, Date updatedAt, Date pushedAt) {
		this.user = user;
		this.contactData = contactData;
		this.verified = verified;
		this.verifiedAt = verifiedAt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.pushedAt = pushedAt;
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

	public String getContactData() {
		return this.contactData;
	}

	public void setContactData(String contactData) {
		this.contactData = contactData;
	}

	public boolean isVerified() {
		return this.verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Date getVerifiedAt() {
		return this.verifiedAt;
	}

	public void setVerifiedAt(Date verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	@Override
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public Date getPushedAt() {
		return pushedAt;
	}

	@Override
	public void setPushedAt(Date pushedAt) {
		this.pushedAt = pushedAt;
	}

	@Override
	public User getUpdatedBy() {
		return updatedBy;
	}

	@Override
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public User getPushedBy() {
		return pushedBy;
	}

	@Override
	public void setPushedBy(User pushedBy) {
		this.pushedBy = pushedBy;
	}
}
