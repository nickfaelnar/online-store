package com.nfaelnar.onlinestore.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nfaelnar.onlinestore.base.BaseDao;
import com.nfaelnar.onlinestore.model.OrderItem;

@Repository
public class OrderItemDao extends BaseDao {
	
	private final String GET_ALL_ORDER_ITEMS = "SELECT * FROM order_items WHERE order_id = ?";
	
	private final String INSERT_ORDER_ITEM = "INSERT INTO order_items(order_id, item_id) VALUES (?, ?)";
	private final String DELETE_ORDER_ITEM = "DELETE FROM order_items WHERE order_item_id = ?";
	
	public List<OrderItem> getOrderItems(int orderId) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OrderItem> orderItems = getJdbcTemplate().query(GET_ALL_ORDER_ITEMS, new Object[] { orderId }, new BeanPropertyRowMapper(OrderItem.class));
		return orderItems;
	}
	
	public void addOrderItem(OrderItem orderItem) {
		getJdbcTemplate().update(INSERT_ORDER_ITEM, orderItem.getOrderId(), orderItem.getItemId());
	}
	
	public void deleteOrderItem(int orderItemId) {
		getJdbcTemplate().update(DELETE_ORDER_ITEM, orderItemId);
	}
	
}
