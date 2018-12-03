package com.nfaelnar.onlinestore.model;

public class Address {
	
	private int addressId;
	private int userId;
	private String houseBldgSt;
	private String brgy;
	private String city;
	private String province;
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getHouseBldgSt() {
		return houseBldgSt;
	}
	public void setHouseBldgSt(String houseBldgSt) {
		this.houseBldgSt = houseBldgSt;
	}
	public String getBrgy() {
		return brgy;
	}
	public void setBrgy(String brgy) {
		this.brgy = brgy;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	

}
