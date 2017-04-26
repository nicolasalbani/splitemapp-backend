package com.splitemapp.commons.domain;

import java.util.List;

public class SingleUserExpenses {

	private String fullName;
	private List<UserExpense> expenseList;

	public SingleUserExpenses(String fullName, List<UserExpense> expenseList) {
		this.fullName = fullName;
		this.expenseList = expenseList;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public List<UserExpense> getExpenseList() {
		return expenseList;
	}
	public void setExpenseList(List<UserExpense> expenseList) {
		this.expenseList = expenseList;
	} 

	/**
	 * Returns the sum of all expenses listed for this user
	 * @return
	 */
	public float getFullAmount(){
		float fullAmount = 0;

		for(UserExpense userExpense:expenseList){
			fullAmount += userExpense.getExpense().floatValue();
		}

		return fullAmount;
	}

}
