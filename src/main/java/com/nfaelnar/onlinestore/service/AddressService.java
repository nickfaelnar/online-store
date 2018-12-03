package com.nfaelnar.onlinestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfaelnar.onlinestore.dao.AddressDao;
import com.nfaelnar.onlinestore.dao.CreditCardDao;
import com.nfaelnar.onlinestore.dao.UserDao;
import com.nfaelnar.onlinestore.model.Address;
import com.nfaelnar.onlinestore.model.CreditCard;
import com.nfaelnar.onlinestore.model.User;

@Service
public class AddressService {
	
	@Autowired
	AddressDao addressDao;
	
	public Address getAddress(int addressId) {
		return addressDao.getAddress(addressId);
	}

	public List<Address> getUserAddresses(int userId) {
		return addressDao.getUserAddresses(userId);
	}
	
}
