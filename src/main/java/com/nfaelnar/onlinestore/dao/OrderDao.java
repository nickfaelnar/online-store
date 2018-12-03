package com.nfaelnar.onlinestore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nfaelnar.onlinestore.base.BaseDao;
import com.nfaelnar.onlinestore.constants.OrderStatus;
import com.nfaelnar.onlinestore.model.Orders;

@Repository
public class OrderDao extends BaseDao {
	
	private final String GET_ALL_USER_ORDERS = "SELECT * FROM orders WHERE user_id = ? AND status = ?";
	private final String GET_USER_ORDER_BY_STATUS = "SELECT * FROM orders WHERE user_id = ? AND status = ?";
	
	private final String INSERT_ORDER = "INSERT INTO orders (user_id, status, card_id, address_id) VALUES (?, ?, ?, ?)";
	private final String UPDATE_ORDER_STATUS = "UPDATE orders SET status  = ? WHERE order_id = ?";
	
	public List<Orders> getUserOrders(int userId, OrderStatus status) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Orders> userOrders = getJdbcTemplate().query(GET_ALL_USER_ORDERS, new Object[] { userId, status.getStatusCode() }, new BeanPropertyRowMapper(Orders.class));
		return userOrders;
	}
	
	public Orders getUserOrdersByStatus(int userId, OrderStatus status) {
		Orders order = null;
		try {
		order = (Orders) getJdbcTemplate().queryForObject(GET_USER_ORDER_BY_STATUS, new Object[] { userId, status.getStatusCode() },
				new BeanPropertyRowMapper<>(Orders.class));
		} catch (EmptyResultDataAccessException e) {}
		return order;
	}
	
	public int addOrder(Orders order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			        PreparedStatement statement = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
			        statement.setInt(1, order.getUserId());
			        statement.setInt(2, OrderStatus.INPROGRESS.getStatusCode());
			        statement.setInt(3, order.getCardId());
			        statement.setInt(4, order.getAddressId());
					return statement;
				}
			}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public void updateOrder(int orderId, OrderStatus status) {
		getJdbcTemplate().update(UPDATE_ORDER_STATUS, status.getStatusCode(), orderId);
	}
	
}
