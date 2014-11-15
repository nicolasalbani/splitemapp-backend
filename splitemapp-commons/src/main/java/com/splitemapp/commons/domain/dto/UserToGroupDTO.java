package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.splitemapp.commons.domain.UserToGroup;

public class UserToGroupDTO implements java.io.Serializable {

	private static final long serialVersionUID = 9173640802573250421L;

	private Long id;
	private UserDTO userDTO;
	private GroupDTO groupDTO;
	private UserToGroupStatusDTO userToGroupStatusDTO;
	private Date createdAt;
	private Date updatedAt;
	private boolean isAdmin;
	
	public UserToGroupDTO(){}

	public UserToGroupDTO(UserToGroup userToGroup) {
		this.userDTO = new UserDTO(userToGroup.getUser());
		this.groupDTO = new GroupDTO(userToGroup.getGroup());
		this.userToGroupStatusDTO = new UserToGroupStatusDTO(userToGroup.getUserToGroupStatus());
		this.isAdmin = userToGroup.isIsAdmin();

	}

	public UserToGroupDTO(UserDTO user, GroupDTO groupDTO,
			UserToGroupStatusDTO userToGroupStatusDTO, boolean isAdmin) {
		this.userDTO = user;
		this.groupDTO = groupDTO;
		this.userToGroupStatusDTO = userToGroupStatusDTO;
		this.isAdmin = isAdmin;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return this.userDTO;
	}

	public void setUserDTO(UserDTO user) {
		this.userDTO = user;
	}

	public GroupDTO getGroupDTO() {
		return this.groupDTO;
	}

	public void setGroupDTO(GroupDTO groupDTO) {
		this.groupDTO = groupDTO;
	}

	public UserToGroupStatusDTO getUserToGroupStatusDTO() {
		return this.userToGroupStatusDTO;
	}

	public void setUserToGroupStatusDTO(UserToGroupStatusDTO userToGroupStatusDTO) {
		this.userToGroupStatusDTO = userToGroupStatusDTO;
	}

	public boolean isIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
