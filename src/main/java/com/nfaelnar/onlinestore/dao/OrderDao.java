package com.nfaelnar.onlinestore.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.nfaelnar.onlinestore.base.BaseDao;
import com.nfaelnar.onlinestore.constants.OrderStatus;
import com.nfaelnar.onlinestore.model.Order;

public class OrderDao extends BaseDao {
	
	private final String GET_ALL_USER_ORDERS = "SELECT * FROM order WHERE user_id = ? AND status = ?";
	private final String GET_USER_ORDER_BY_STATUS = "SELECT * FROM order WHERE user_id = ? AND status = ?";
	
	private final String INSERT_ORDER = "INSERT INTO order (user_id, status) VALUES (?, ?)";
	private final String UPDATE_ORDER = "UPDATE order SET status  = ?, card_info  = ?, address_info = ? WHERE order_id = ? AND user_id = ?";
	
	public List<Order> getUserOrders(int userId, OrderStatus status) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Order> userOrders = getJdbcTemplate().query(GET_ALL_USER_ORDERS, new Object[] { userId, status.getStatusCode() }, new BeanPropertyRowMapper(Order.class));
		return userOrders;
	}
	
	public Order getUserOrdersByStatus(int userId, OrderStatus status) {
		Order order = (Order) getJdbcTemplate().queryForObject(GET_USER_ORDER_BY_STATUS, new Object[] { userId, status.getStatusCode() },
				new BeanPropertyRowMapper<>(Order.class));
		return order;
	}
	
	public void addOrder(Order order) {
		getJdbcTemplate().update(INSERT_ORDER, order.getUserId(), OrderStatus.INPROGRESS.getStatusCode());
	}
	
	public void updateOrder(Order order) {
		getJdbcTemplate().update(UPDATE_ORDER, order.getStatus(), order.getCardInfo(), order.getAddressInfo(), order.getOrderId(), order.getUserId());
	}
	
}
