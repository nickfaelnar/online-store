package com.nfaelnar.onlinestore.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nfaelnar.onlinestore.base.BaseDao;
import com.nfaelnar.onlinestore.model.Address;
import com.nfaelnar.onlinestore.model.Item;

@Repository
public class ItemDao extends BaseDao {

	private final String GET_ITEMS = "SELECT * FROM item";
	
	private final String GET_ITEM = "SELECT * FROM item WHERE item_id = ?";
	
	public List<Item> getItems() {
		return getJdbcTemplate().query(GET_ITEMS, new BeanPropertyRowMapper<>(Item.class));
	}
	
	public Item getItem(int itemId) {
		return (Item) getJdbcTemplate().queryForObject(GET_ITEM, new Object[] {itemId },
				new BeanPropertyRowMapper<>(Item.class));
	}
	
}
