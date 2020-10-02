package com.webmobilecart.mobileapp.domain;

import java.io.Serializable;

public class Order implements Serializable{
	int idUser;
	int ProductId;
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		ProductId = productId;
	}
	public boolean equals(Object obj)
	{
		Order order = (Order)obj;
		if(idUser == order.idUser&&ProductId == order.ProductId){
			return true;
		}
		else {
			return false;
		}
		
	}
}
