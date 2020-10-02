package com.webmobilecart.mobileapp;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webmobilecart.mobileapp.domain.Order;
import com.webmobilecart.mobileapp.services.OrderService;

public class OrderController {
	@Autowired
	OrderService orderService;
	
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@RequestMapping(value = "/newOrderForm", method = RequestMethod.GET)
	public ModelAndView newOrderForm() {
		ModelAndView modelView;
		
 		modelView = new ModelAndView("NewOrderForm");
 		modelView.addObject("order", new Order());
		return modelView;
	}

	@RequestMapping(value = "/processNewOrderForm", method = RequestMethod.POST)
	public ModelAndView processNewRoomForm(@Valid Order o, BindingResult result, HttpSession session) 
	{
		ModelAndView modelView;
		
		if (result.hasErrors()) {
			
			modelView = new ModelAndView("NewOrderForm", "order", o);
			return modelView;
			
		}
		else
		{
		System.out.println(o);
		orderService.addNewOrder(o);
 		modelView = new ModelAndView("newOrderSuccess");
 		session.setAttribute("Order",o);
 		modelView.addObject("Order", o);
		return modelView;
		}
	}
}
