package com.splitemapp.commons.comparator;

import java.util.Comparator;

import com.splitemapp.commons.domain.SingleUserExpenses;

public class SingleUserExpensesComparator implements Comparator<SingleUserExpenses>{

	@Override
	public int compare(SingleUserExpenses lhs, SingleUserExpenses rhs) {
		// This orders the SingleUserExpenses objects in a list from the one containing the newest user expense to the oldest
		return rhs.getLastUpdatedExpenseDate().compareTo(lhs.getLastUpdatedExpenseDate());
	}

}
