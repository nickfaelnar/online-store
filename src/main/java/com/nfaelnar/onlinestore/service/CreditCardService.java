package com.nfaelnar.onlinestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfaelnar.onlinestore.dao.CreditCardDao;
import com.nfaelnar.onlinestore.model.Address;
import com.nfaelnar.onlinestore.model.CreditCard;

@Service
public class CreditCardService {

	@Autowired
	CreditCardDao creditCardDao;
	
	public List<CreditCard> getUserCreditCards(int userId) {
		return creditCardDao.getUserCreditCards(userId);
	}
	
	public CreditCard getCreditCard(int cardId) {
		return creditCardDao.getUserCreditCard(cardId);
	}
	
}
