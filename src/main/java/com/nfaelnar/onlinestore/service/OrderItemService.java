package com.nfaelnar.onlinestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfaelnar.onlinestore.dao.ItemDao;
import com.nfaelnar.onlinestore.dao.OrderItemDao;
import com.nfaelnar.onlinestore.dao.UserDao;
import com.nfaelnar.onlinestore.model.Address;
import com.nfaelnar.onlinestore.model.CreditCard;
import com.nfaelnar.onlinestore.model.Item;
import com.nfaelnar.onlinestore.model.OrderItem;
import com.nfaelnar.onlinestore.model.Orders;
import com.nfaelnar.onlinestore.model.User;

@Service
public class OrderItemService {
	
	@Autowired
	OrderService orderService; 
	
	@Autowired
	CreditCardService creditCardService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	ItemDao itemDao;
	
	@Autowired
	OrderItemDao orderItemDao;
	
	public void addItemToCart(int itemId, int userId) {
		Orders order = orderService.getInprogressOrder(userId);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(itemId);
		orderItem.setOrderId(order.getOrderId());
		
		orderItemDao.addOrderItem(orderItem);
	}
	
	public void deleteCartItem(int orderItemId) {
		orderItemDao.deleteOrderItem(orderItemId);
	}
	
	public List<OrderItem> getOrderItems(int orderId) {
		return orderItemDao.getOrderItems(orderId);
	}

}
