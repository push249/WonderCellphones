package com.webmobilecart.mobileapp.dao;
import com.webmobilecart.mobileapp.domain.Order;

import com.webmobilecart.mobileapp.exceptions.*;
public interface OrderDAO {
	public void addOrder(int idUser, int ProductId);
	public int removeOrder(int i, int j);
}
