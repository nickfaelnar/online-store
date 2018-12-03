package com.nfaelnar.onlinestore.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.nfaelnar.onlinestore.base.BaseDao;
import com.nfaelnar.onlinestore.model.CreditCard;

public class CreditCardDao extends BaseDao {
	
	private final String GET_ALL_USER_CREDITCARD = "SELECT * FROM credit_card WHERE user_id = ?";
	private final String GET_USER_CREDITCARD = "SELECT * FROM credit_card WHERE user_id = ? AND card_id = ?";
	private final String INSERT_CREDITCARD = "INSERT INTO credit_card (user_id, card_num, exp_date) VALUES (?, ?, ?)";
	private final String UPDATE_CREDITCARD = "UPDATE credit_card SET card_num = ?, exp_date = ? WHERE card_id = ?  AND user_id = ?";
	private final String DELETE_CREDITCARD = "DELETE FROM credit_card WHERE card_id = ? AND user_id = ?";
	
	public List<CreditCard> getUserCreditCards(int userId) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<CreditCard> userCards = getJdbcTemplate().query(GET_ALL_USER_CREDITCARD, new Object[] { userId}, new BeanPropertyRowMapper(CreditCard.class));
		return userCards;
	}
	
	public CreditCard getUserCreditCard(int userId, int cardId) {
		CreditCard creditCard = (CreditCard) getJdbcTemplate().queryForObject(GET_USER_CREDITCARD, new Object[] { userId, userId },
				new BeanPropertyRowMapper<>(CreditCard.class));
		return creditCard;
	}
	
	public void addCreditCard(CreditCard creditCard) {
		getJdbcTemplate().update(INSERT_CREDITCARD, creditCard.getUserId(), creditCard.getCardNum(), creditCard.getExpirationDate());
	}
	
	public void updateCreditCard(CreditCard creditCard) {
		getJdbcTemplate().update(UPDATE_CREDITCARD, creditCard.getCardNum(), creditCard.getExpirationDate(), creditCard.getCardId(), creditCard.getUserId());
	}
	
	public void deleteCreditCard(CreditCard creditCard) {
		getJdbcTemplate().update(DELETE_CREDITCARD, creditCard.getCardId(), creditCard.getUserId());
	}
	
}
