package com.nfaelnar.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfaelnar.onlinestore.dao.UserDao;
import com.nfaelnar.onlinestore.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public User validateUser(String userName, String password) {
		User user = userDao.getUser(userName, password);
		return user;
	}

}
