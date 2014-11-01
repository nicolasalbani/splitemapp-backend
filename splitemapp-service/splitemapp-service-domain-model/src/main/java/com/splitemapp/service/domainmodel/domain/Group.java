package com.splitemapp.service.domainmodel.domain;

// Generated Sep 15, 2014 8:09:15 PM by Hibernate Tools 4.0.0

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
import javax.persistence.UniqueConstraint;

/**
 * Group generated by hbm2java
 */
@Entity
@Table(name = "`group`", catalog = "splitshares", uniqueConstraints = @UniqueConstraint(columnNames = "cod"))
public class Group implements java.io.Serializable {

	private static final long serialVersionUID = 7350514135837582153L;
	private Long id;
	private GroupStatus groupStatus;
	private String cod;
	private String title;
	private String imgCover;
	private Date createdAt;
	private Date updatedAt;
	private Set<UserToGroup> userToGroups = new HashSet<UserToGroup>(0);

	public Group() {
	}

	public Group(GroupStatus groupStatus, String cod, String title,
			Date createdAt, Date updatedAt) {
		this.groupStatus = groupStatus;
		this.cod = cod;
		this.title = title;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Group(GroupStatus groupStatus, String cod, String title,
			String imgCover, Date createdAt, Date updatedAt,
			Set<UserToGroup> userToGroups) {
		this.groupStatus = groupStatus;
		this.cod = cod;
		this.title = title;
		this.imgCover = imgCover;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userToGroups = userToGroups;
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
	@JoinColumn(name = "status_id", nullable = false)
	public GroupStatus getGroupStatus() {
		return this.groupStatus;
	}

	public void setGroupStatus(GroupStatus groupStatus) {
		this.groupStatus = groupStatus;
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

	@Column(name = "img_cover", length = 128)
	public String getImgCover() {
		return this.imgCover;
	}

	public void setImgCover(String imgCover) {
		this.imgCover = imgCover;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false, length = 19)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	public Set<UserToGroup> getUserToGroups() {
		return this.userToGroups;
	}

	public void setUserToGroups(Set<UserToGroup> userToGroups) {
		this.userToGroups = userToGroups;
	}

}
