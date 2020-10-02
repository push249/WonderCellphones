package com.webmobilecart.mobileapp.dao;


import com.webmobilecart.mobileapp.domain.User;
import com.webmobilecart.mobileapp.exceptions.*;

public interface UserDAO {
	public int addUser(String firstName, String lastName, String userName, String eMail, String password, String address, String contactNo);
	public int addUser(User user);
}
