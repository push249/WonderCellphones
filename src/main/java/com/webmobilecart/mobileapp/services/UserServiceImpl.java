package com.webmobilecart.mobileapp.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webmobilecart.mobileapp.dao.UserDAO;
import com.webmobilecart.mobileapp.domain.User;
import com.webmobilecart.mobileapp.domain.UserAccount;
import com.webmobilecart.mobileapp.exceptions.*;

@Service
@Transactional

public class UserServiceImpl implements UserService{
		@Autowired
		private UserDAO userDao;
		

		@Override
		public void addNewUser(User user) {
			 userDao.addUser(user.getFirstName(),user.getLastName(),user.getUserName(),user.geteMail(),user.getPassword(), user.getAddress(), user.getContactNo());
			 userDao.addUser(user);
		}


		@Override
		public List<User> getUserList() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public UserAccount extractAcctFromAuthorization(String auth) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public User getUserWithId(long id) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public User updateUser(int id, User newUser) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public int removeUserWithId(int id) {
			// TODO Auto-generated method stub
			return 0;
		}
}
