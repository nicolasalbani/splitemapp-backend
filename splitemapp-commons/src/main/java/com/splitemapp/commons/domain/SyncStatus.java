package com.splitemapp.commons.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.splitemapp.commons.constants.TableName;

@Entity(name = TableName.SYNC_STATUS)
@Table(name = TableName.SYNC_STATUS, catalog = "splitemapp", uniqueConstraints = @UniqueConstraint(columnNames = "table_name"))
public class SyncStatus implements java.io.Serializable {

	private static final long serialVersionUID = 8700853389002247470L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Short id;
	
	@Column(name = "table_name", unique = true, nullable = false, length = 64)
	private String tableName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_pull_at", length = 19)
	private Date lastPullAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_pull_success_at", length = 19)
	private Date lastPullSuccessAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_push_at", length = 19)
	private Date lastPushAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_push_success_at", length = 19)
	private Date lastPushSuccessAt;
	
	public SyncStatus() {
	}

	public SyncStatus(Short id, String tableName) {
		this.id = id;
		this.tableName = tableName;
	}
	
	public SyncStatus(Short id, String tableName, Date lastPushAt, Date lastPullAt, Date lastPushSuccessAt, Date lastPullSuccessAt) {
		this.id = id;
		this.tableName = tableName;
		this.lastPushAt = lastPushAt;
		this.lastPushSuccessAt = lastPushSuccessAt;
		this.lastPullAt = lastPullAt;
		this.lastPullSuccessAt = lastPullSuccessAt;
	}

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getLastPushAt() {
		return lastPushAt;
	}

	public void setLastPushAt(Date lastPushAt) {
		this.lastPushAt = lastPushAt;
	}

	public Date getLastPushSuccessAt() {
		return lastPushSuccessAt;
	}

	public void setLastPushSuccessAt(Date lastPushSuccessAt) {
		this.lastPushSuccessAt = lastPushSuccessAt;
	}

	public Date getLastPullAt() {
		return lastPullAt;
	}

	public void setLastPullAt(Date lastPullAt) {
		this.lastPullAt = lastPullAt;
	}
	
	public Date getLastPullSuccessAt() {
		return lastPullSuccessAt;
	}

	public void setLastPullSuccessAt(Date lastPullSuccessAt) {
		this.lastPullSuccessAt = lastPullSuccessAt;
	}

}