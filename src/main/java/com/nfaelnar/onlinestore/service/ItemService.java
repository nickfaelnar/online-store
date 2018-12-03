package com.nfaelnar.onlinestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfaelnar.onlinestore.dao.ItemDao;
import com.nfaelnar.onlinestore.dao.UserDao;
import com.nfaelnar.onlinestore.model.Item;
import com.nfaelnar.onlinestore.model.User;

@Service
public class ItemService {
	
	@Autowired
	ItemDao itemDao;
	
	public List<Item> getAllItems() {
		return itemDao.getItems();
	}
	
	public Item getItem(int itemId) {
		return itemDao.getItem(itemId);
	}

}
