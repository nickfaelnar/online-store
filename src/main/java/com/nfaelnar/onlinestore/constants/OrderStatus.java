package com.nfaelnar.onlinestore.constants;

public enum OrderStatus {
	INPROGRESS(0),
	FULFILLED(1);
	
	private int statusCode;
	
	OrderStatus(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	

}
