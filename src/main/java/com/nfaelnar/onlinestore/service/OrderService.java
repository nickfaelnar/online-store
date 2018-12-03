package com.nfaelnar.onlinestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfaelnar.onlinestore.constants.OrderStatus;
import com.nfaelnar.onlinestore.dao.ItemDao;
import com.nfaelnar.onlinestore.dao.OrderDao;
import com.nfaelnar.onlinestore.dao.UserDao;
import com.nfaelnar.onlinestore.model.Address;
import com.nfaelnar.onlinestore.model.CreditCard;
import com.nfaelnar.onlinestore.model.Item;
import com.nfaelnar.onlinestore.model.Orders;
import com.nfaelnar.onlinestore.model.User;

@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	CreditCardService creditCardService;
	
	@Autowired
	AddressService addressService;
	
	public Orders getInprogressOrder(int userId) {
		Orders order = orderDao.getUserOrdersByStatus(userId, OrderStatus.INPROGRESS);
		if (null == order) {
			List<CreditCard> creditCardList = creditCardService.getUserCreditCards(userId);
			List<Address> addressList = addressService.getUserAddresses(userId);
			order = new Orders();
			order.setUserId(userId);
			order.setCardId(creditCardList.get(0).getCardId());
			order.setAddressId(addressList.get(0).getAddressId());
			int orderId = orderDao.addOrder(order);
			order.setOrderId(orderId);
		}
		return order;
	}
	
	public void checkout(int orderId) {
		orderDao.updateOrder(orderId, OrderStatus.FULFILLED);
	}

}
