package com.nfaelnar.onlinestore.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nfaelnar.onlinestore.model.Address;
import com.nfaelnar.onlinestore.model.CreditCard;
import com.nfaelnar.onlinestore.model.Item;
import com.nfaelnar.onlinestore.model.OrderItem;
import com.nfaelnar.onlinestore.model.Orders;
import com.nfaelnar.onlinestore.service.AddressService;
import com.nfaelnar.onlinestore.service.CreditCardService;
import com.nfaelnar.onlinestore.service.ItemService;
import com.nfaelnar.onlinestore.service.OrderItemService;
import com.nfaelnar.onlinestore.service.OrderService;
import com.nfaelnar.onlinestore.service.UserService;

@Controller
public class CheckoutController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	CreditCardService creditCardService;
	
	@RequestMapping(value="/cart")
	public String showAllItems(ModelMap model, HttpSession session) {
		if (!populateCheckoutPage(model, session)) {
			model.addAttribute("error", "Add items first!");
		}
		return "order";
	}
	
	private boolean populateCheckoutPage(ModelMap model, HttpSession session) {
		Orders order = orderService.getInprogressOrder((Integer) session.getAttribute("userId"));
		if (null == order) {
			return false;
		}
		List<OrderItem> orderItems = orderItemService.getOrderItems(order.getOrderId());
		if (CollectionUtils.isEmpty(orderItems)) {
			return false;
		}
		Address address = addressService.getAddress(order.getAddressId());
		CreditCard creditCard = creditCardService.getCreditCard(order.getAddressId());
		
		List<Item> items = new ArrayList<>();
		BigDecimal total = new BigDecimal(0);
		for (OrderItem orderItem : orderItems) {
			Item item = itemService.getItem(orderItem.getItemId());
			item.setOrderItemId(orderItem.getOrderItemId());
			items.add(item);
			total = total.add(item.getPrice());
		}
		model.addAttribute("order", order);
		model.addAttribute("total", total);
		model.addAttribute("items", items);
		model.addAttribute("address", address);
		model.addAttribute("creditCard", creditCard);
		return true;
	}
	
	@RequestMapping(value="/cart/delete")
	public String deleteItem(ModelMap model, @RequestParam int orderItemId, HttpSession session) {
		orderItemService.deleteCartItem(orderItemId);
		if (!populateCheckoutPage(model, session)) {
			model.addAttribute("error", "Add items first!");
		}
		return "order";
	}
	
	@RequestMapping(value="/checkout")
	public ModelAndView checkout(ModelMap model, @RequestParam int orderId, HttpSession session) {
		orderService.checkout(orderId);
		return new ModelAndView("redirect:/items");
	}
	
}
