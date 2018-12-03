package com.nfaelnar.onlinestore.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.nfaelnar.onlinestore.base.BaseDao;
import com.nfaelnar.onlinestore.model.OrderItem;

public class OrderItemDao extends BaseDao {
	
	private final String GET_ALL_ORDER_ITEMS = "SELECT * FROM order_item WHERE order_id = ?";
	
	private final String INSERT_ORDER_ITEM = "INSERT INTO order_item(order_id, item_id, item_info, item_quantity) VALUES (?, ?, ?, ?)";
	private final String UPDATE_ORDER_ITEM = "UPDATE order_item SET item_info = ?, item_quantity = ? WHERE order_id = ? AND item_id = ?";
	private final String DELETE_ORDER_ITEM = "DELETE FROM order_items WHERE order_id = ? AND item_id = ?";
	
	public List<OrderItem> getOrderItems(int orderId) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OrderItem> orderItems = getJdbcTemplate().query(GET_ALL_ORDER_ITEMS, new Object[] { orderId }, new BeanPropertyRowMapper(OrderItem.class));
		return orderItems;
	}
	
	public void addOrderItem(OrderItem orderItem) {
		getJdbcTemplate().update(INSERT_ORDER_ITEM, orderItem.getOrderId(), orderItem.getItemId(), orderItem.getItemInfo(), orderItem.getItemQuantity());
	}
	
	public void updateOrderItem(OrderItem orderItem) {
		getJdbcTemplate().update(UPDATE_ORDER_ITEM, orderItem.getItemInfo(), orderItem.getItemQuantity(), orderItem.getOrderId(), orderItem.getItemId());
	}
	
	public void deleteOrderItem(OrderItem orderItem) {
		getJdbcTemplate().update(DELETE_ORDER_ITEM, orderItem.getOrderId(), orderItem.getItemId());
	}
	
}
