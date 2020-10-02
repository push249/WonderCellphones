package com.webmobilecart.mobileapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webmobilecart.mobileapp.domain.User;
import com.webmobilecart.mobileapp.services.UserService;

public class UserController {
	@Autowired
	UserService userService;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@RequestMapping(value = "/newUserForm", method = RequestMethod.GET)
	public ModelAndView newCustomerForm() {
		ModelAndView modelView;
		
 		modelView = new ModelAndView("NewUserForm");
 		modelView.addObject("user", new User());
		return modelView;
	}
	@RequestMapping(value = "/processNewUserForm", method = RequestMethod.POST)
	public ModelAndView processNewCustomerForm(@Valid User user, BindingResult result, HttpSession session) 
	{
		ModelAndView modelView;
		
		if (result.hasErrors()) {
			
			modelView = new ModelAndView("NewUserForm", "user",user);
			System.out.println(" Inside Result");
			return modelView;
			
		}
		else
		{
		System.out.println(user);
		userService.addNewUser(user);
 		modelView = new ModelAndView("newCustomerProfileSuccess");
 		session.setAttribute("User", user);
 		modelView.addObject("User", user);
		return modelView;
		}
	}
}
