package com.nfaelnar.onlinestore.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nfaelnar.onlinestore.model.User;
import com.nfaelnar.onlinestore.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView landing(ModelMap model, HttpSession session) {
		if (null == session.getAttribute("userId")) {
			return new ModelAndView("/login");
		} else {
			return new ModelAndView("redirect:/items");
		}
		
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView login(ModelMap model, @RequestParam String userName, @RequestParam String password, HttpSession session) {
		User user = userService.validateUser(userName, password);
		
		if (null == user) {
			model.put("error", "Invalid Credentials. Please try again.");
			return new ModelAndView("/login");
		}
		
		session.setAttribute("userId", user.getUserId());
		model.put("name", user.getFirstName());
		
		return new ModelAndView("redirect:/items");
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView login(ModelMap model, HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/login");
	}
	
	
}
