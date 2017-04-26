package com.splitemapp.commons.comparator;

import java.util.Comparator;
import java.util.Date;

import com.splitemapp.commons.domain.SingleUserExpenses;

public class SingleUserExpensesComparator implements Comparator<SingleUserExpenses>{

	@Override
	public int compare(SingleUserExpenses lhs, SingleUserExpenses rhs) {
		// Getting the latest updated expense from "lhs"
		Date lhsUpdatedAt = lhs.getExpenseList().get(0).getUpdatedAt();

		// Getting the latest updated expense from "rhs"
		Date rhsUpdatedAt = rhs.getExpenseList().get(0).getUpdatedAt();

		// Returning the comparison between the right hand side and the left hand side
		return rhsUpdatedAt.compareTo(lhsUpdatedAt);
	}

}
