package com.splitemapp.commons.comparator;

import java.util.Comparator;

import com.splitemapp.commons.domain.UserExpense;

public class UserExpenseComparator implements Comparator<UserExpense>{

	@Override
	public int compare(UserExpense lhs, UserExpense rhs) {
		// This orders the UserExpense objects in a list from the newest to the oldest
		return rhs.getUpdatedAt().compareTo(lhs.getUpdatedAt());
	}

}
