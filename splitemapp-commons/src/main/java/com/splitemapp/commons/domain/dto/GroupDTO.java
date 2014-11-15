package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.splitemapp.commons.domain.Group;

public class GroupDTO implements java.io.Serializable {

	private static final long serialVersionUID = 7350514135837582153L;
	
	private Long id;
	private GroupStatusDTO groupStatusDTO;
	private String cod;
	private String title;
	private String imgCover;
	private Date createdAt;
	private Date updatedAt;

	public GroupDTO(Group group) {
		this.groupStatusDTO = new GroupStatusDTO(group.getGroupStatus());
		this.cod = group.getCod();
		this.title = group.getTitle();
		this.imgCover = group.getImgCover();
		this.createdAt = group.getCreatedAt();
		this.updatedAt = group.getUpdatedAt();
	}
	
	public GroupDTO(GroupStatusDTO groupStatusDTO, String cod, String title,
			String imgCover, Date createdAt, Date updatedAt) {
		this.groupStatusDTO = groupStatusDTO;
		this.cod = cod;
		this.title = title;
		this.imgCover = imgCover;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GroupStatusDTO getGroupStatusDTO() {
		return this.groupStatusDTO;
	}

	public void setGroupStatusDTO(GroupStatusDTO groupStatus) {
		this.groupStatusDTO = groupStatus;
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

}
