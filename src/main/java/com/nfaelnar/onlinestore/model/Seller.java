package com.nfaelnar.onlinestore.model;

import java.math.BigDecimal;

public class Seller {

	private int userId;
	private BigDecimal salary;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
}
