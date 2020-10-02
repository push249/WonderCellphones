package com.webmobilecart.mobileapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webmobilecart.mobileapp.dao.OrderDAO;
import com.webmobilecart.mobileapp.domain.Order;
import com.webmobilecart.mobileapp.exceptions.*;
@Service
@Transactional
public class OrderServiceImpl implements OrderService{

		@Autowired
		private OrderDAO orderDao;
		public void addNewOrder(Order o)
		{
			 
				orderDao.addOrder(o.getIdUser(),o.getProductId());
			
		}
}
