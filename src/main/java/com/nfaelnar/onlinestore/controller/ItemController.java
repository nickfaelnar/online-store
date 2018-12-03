package com.nfaelnar.onlinestore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nfaelnar.onlinestore.model.Item;
import com.nfaelnar.onlinestore.service.ItemService;
import com.nfaelnar.onlinestore.service.OrderItemService;
import com.nfaelnar.onlinestore.service.UserService;

@Controller
public class ItemController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	OrderItemService orderItemService;

	@RequestMapping(value="/items")
	public String showAllItems(ModelMap model, HttpSession session) {
		List<Item> itemList = itemService.getAllItems();
		model.addAttribute("itemList", itemList);
		return "main";
	}
	
	@RequestMapping(value="/cart", method = RequestMethod.POST)
	public String addToCart(ModelMap model, @RequestParam int itemId, HttpSession session) {
		orderItemService.addItemToCart(itemId, (Integer)session.getAttribute("userId"));
		List<Item> itemList = itemService.getAllItems();
		model.addAttribute("itemList", itemList);
		model.addAttribute("success", "Successfully added item to cart!");
		return "main";
	}
}
