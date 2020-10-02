package com.webmobilecart.mobileapp.services;
import java.util.List;

import com.webmobilecart.mobileapp.domain.User;
import com.webmobilecart.mobileapp.domain.UserAccount;

public interface UserService {
	public void addNewUser(User user);

	public List<User> getUserList();

	public UserAccount extractAcctFromAuthorization(String auth);

	public User getUserWithId(long id);

	public User updateUser(int id, User newUser);

	public int removeUserWithId(int id);
}
