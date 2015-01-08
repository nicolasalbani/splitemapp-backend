package com.splitemapp.commons.domain.dto.response;

import java.util.Set;

import com.splitemapp.commons.domain.dto.UserExpenseDTO;

public class PullUserExpensesResponse extends ServiceResponse{

	private Set<UserExpenseDTO> userExpenseDTOs;

	public PullUserExpensesResponse() {}

	public PullUserExpensesResponse(Boolean success, Set<UserExpenseDTO> userExpenseDTOs) {
		super(success);
		this.userExpenseDTOs = userExpenseDTOs;
	}

	public Set<UserExpenseDTO> getUserExpenseDTOs() {
		return userExpenseDTOs;
	}

	public void setUserExpenseDTOs(Set<UserExpenseDTO> userExpenseDTOs) {
		this.userExpenseDTOs = userExpenseDTOs;
	}
}
