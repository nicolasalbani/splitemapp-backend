package com.splitemapp.commons.domain.dto;

import java.util.Date;

import com.splitemapp.commons.domain.UserInvite;

public class UserInviteDTO implements java.io.Serializable {

	private static final long serialVersionUID = -2499430771175807337L;

	private Long id;
	private UserDTO userDTO;
	private ProjectDTO projectDTO;
	private InviteStatusDTO inviteStatusDTO;
	private String email;
	private Date createdAt;
	private Date updatedAt;
	
	public UserInviteDTO(){}

	public UserInviteDTO(UserInvite userInvite) {
		this.userDTO = new UserDTO(userInvite.getUser());
		this.projectDTO = new ProjectDTO(userInvite.getProject());
		this.inviteStatusDTO = new InviteStatusDTO(userInvite.getInviteStatus());
		this.createdAt = userInvite.getCreatedAt();
		this.updatedAt = userInvite.getUpdatedAt();
	}

	public UserInviteDTO(UserDTO user, ProjectDTO projectDTO, InviteStatusDTO inviteStatusDTO,
			String email, Date createdAt, Date updatedAt) {
		this.userDTO = user;
		this.projectDTO = projectDTO;
		this.inviteStatusDTO = inviteStatusDTO;
		this.email = email;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public ProjectDTO getProjectDTO() {
		return this.projectDTO;
	}

	public void setProjectDTO(ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

	public InviteStatusDTO getInviteStatusDTO() {
		return this.inviteStatusDTO;
	}

	public void setInviteStatusDTO(InviteStatusDTO inviteStatusDTO) {
		this.inviteStatusDTO = inviteStatusDTO;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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
