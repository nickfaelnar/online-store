package com.nfaelnar.onlinestore.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.nfaelnar.onlinestore.base.BaseDao;
import com.nfaelnar.onlinestore.model.User;

public class UserDao extends BaseDao {
	
	private final String GET_USER = "SELECT * FROM user WHERE username = ? AND password = ?";
	
	public User getUser(String userName, String password) {
		User user = (User) getJdbcTemplate().queryForObject(GET_USER, new Object[] { userName, password },
				new BeanPropertyRowMapper<>(User.class));
		return user;
	}

}
