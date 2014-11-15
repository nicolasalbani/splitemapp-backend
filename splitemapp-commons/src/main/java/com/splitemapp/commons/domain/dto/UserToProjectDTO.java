package com.splitemapp.commons.domain.dto;


import java.math.BigDecimal;
import java.util.Date;

import com.splitemapp.commons.domain.UserToProject;

public class UserToProjectDTO implements java.io.Serializable {

	private static final long serialVersionUID = 7814066964126398718L;

	private Long id;
	private UserDTO userDTO;
	private ProjectDTO projectDTO;
	private UserToProjectStatusDTO userToProjectStatusDTO;
	private Date createdAt;
	private Date updatedAt;
	private BigDecimal expensesShare;

	public UserToProjectDTO(UserToProject userToProject) {
		this.userDTO = new UserDTO(userToProject.getUser());
		this.projectDTO = new ProjectDTO(userToProject.getProject());
		this.userToProjectStatusDTO = new UserToProjectStatusDTO(userToProject.getUserToProjectStatus());
		this.expensesShare = userToProject.getExpensesShare();
	}

	public UserToProjectDTO(UserDTO userDTO, ProjectDTO projectDTO,
			UserToProjectStatusDTO userToProjectStatusDTO, BigDecimal expensesShare) {
		this.userDTO = userDTO;
		this.projectDTO = projectDTO;
		this.userToProjectStatusDTO = userToProjectStatusDTO;
		this.expensesShare = expensesShare;
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

	public UserToProjectStatusDTO getUserToProjectStatusDTO() {
		return this.userToProjectStatusDTO;
	}

	public void setUserToProjectStatusDTO(UserToProjectStatusDTO userToProjectStatusDTO) {
		this.userToProjectStatusDTO = userToProjectStatusDTO;
	}

	public BigDecimal getExpensesShare() {
		return this.expensesShare;
	}

	public void setExpensesShare(BigDecimal expensesShare) {
		this.expensesShare = expensesShare;
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
