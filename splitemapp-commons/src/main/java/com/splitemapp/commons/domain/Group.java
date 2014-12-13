package com.splitemapp.commons.domain;

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
@Entity(name = "group")
@Table(name = "`group`", catalog = "splitemapp", uniqueConstraints = @UniqueConstraint(columnNames = "cod"))
public class Group implements java.io.Serializable {

	private static final long serialVersionUID = 7350514135837582153L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", nullable = false)
	private GroupStatus groupStatus;
	
	@Column(name = "cod", unique = true, nullable = false, length = 64)
	private String cod;
	
	@Column(name = "title", nullable = false, length = 64)
	private String title;
	
	@Column(name = "img_cover", length = 128)
	private String imgCover;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false, length = 19)
	private Date updatedAt;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GroupStatus getGroupStatus() {
		return this.groupStatus;
	}

	public void setGroupStatus(GroupStatus groupStatus) {
		this.groupStatus = groupStatus;
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

	public String getImgCover() {
		return this.imgCover;
	}

	public void setImgCover(String imgCover) {
		this.imgCover = imgCover;
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

	public Set<UserToGroup> getUserToGroups() {
		return this.userToGroups;
	}

	public void setUserToGroups(Set<UserToGroup> userToGroups) {
		this.userToGroups = userToGroups;
	}

}
