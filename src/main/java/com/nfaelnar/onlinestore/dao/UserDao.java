package com.nfaelnar.onlinestore.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nfaelnar.onlinestore.base.BaseDao;
import com.nfaelnar.onlinestore.model.User;

@Repository
public class UserDao extends BaseDao {
	
	private final String GET_USER = "SELECT * FROM user WHERE username = ? AND password = SHA1(?)";
	
	public User getUser(String userName, String password) {
		User user = null;
		try {
			user = (User) getJdbcTemplate().queryForObject(GET_USER, new Object[] { userName, password },
				new BeanPropertyRowMapper<>(User.class));
		} catch (EmptyResultDataAccessException e) {}	
		return user;
	}
	

}
