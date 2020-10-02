package com.webmobilecart.mobileapp.resthandlers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.UserList;

import com.webmobilecart.mobileapp.dao.*;
import com.webmobilecart.mobileapp.dao.jdbc.*;
import com.webmobilecart.mobileapp.domain.*;
import com.webmobilecart.mobileapp.exceptions.*;
import com.webmobilecart.mobileapp.services.*;
import com.webmobilecart.mobileapp.test.*;

@Path("/webmobilecart")
public class UserRestHandler {
	@Autowired
	private UserService userService;
	private Logger logger = Logger.getLogger(UserRestHandler.class);
	
	
	@GET
	@Path("/user/{id}")
	@Produces("application/xml, application/json")
	public User getUser(@PathParam("id") int id) {
		User user = null;
		
		user = lookupUser(id);
		if (user == null) {
			throw new UnknownResourceException("user id: " + id + " is invalid");
		}
		
		return user;
	}
	@GET
	@Path("/authuser/{id}")
	@Produces("application/xml, application/json")
	public User getUserWithAuth(@PathParam("id") int id, @HeaderParam("Authorization") String auth) throws DbFailure {
		User user = null;
		
		logger.debug("Get Request for user with Authorization Header");
		user = lookupUserWithAuth(id, auth);
		return user;
	}
	@GET
	@Path("/user")
	@Produces("application/xml")
	public UserList getuserList() {
		List<User> userList;
		UserList listOfusers = new UserList();
		
		userList = userService.getUserList();
		listOfusers.setUserList(userList);
		return listOfusers;
	}
	

	@POST
	@Path("/user")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response adduser(User newUser) {
		ResponseBuilder respBuilder;
		
		userService.addNewUser(newUser);
		respBuilder = Response.status(Status.CREATED);
		respBuilder.entity(newUser);
		return respBuilder.build();
	}
	
	/* Only return the user if the authentication information is correct */
	private User lookupUserWithAuth(long id, String auth) throws DbFailure {
		String actualUserName, expectedUserName = "admin";  
		String actualPasswd, expectedPasswd = "admin";

		UserAccount acct = userService.extractAcctFromAuthorization(auth);
		if (acct == null) {
			logger.debug("Authorization Header was null");
			throw new DbFailure("Invalid Authorization Header");
		}
		

		logger.debug("Authorized user found in lookupUserWithAuth():  " + acct);
		actualUserName = acct.getAccountname();
		actualPasswd = acct.getPassword();
		if (!(actualUserName.equals(expectedUserName) && actualPasswd
				.equals(expectedPasswd))) {
			throw new DbFailure("Authorization is invalid for account: " + actualUserName);
		}
		
		return lookupUser(id);

	}
	
	private User lookupUser(long id) {
		User user;
		
		user = userService.getUserWithId(id);
		if (user == null) {
			throw new UnknownResourceException("user id: " + id + " is invalid");
		}
		
		return user;
	}
	
	@PUT
	@Path("/user/{id}")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response updateuser(@PathParam("id") int id, User newUser) {
		ResponseBuilder respBuilder;
		User updatedUser;
		
		updatedUser = userService.updateUser(id, newUser);
		if (updatedUser == null) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.status(Status.OK);
			respBuilder.entity(updatedUser);
		}
		
		return respBuilder.build();
	}

	@DELETE
	@Path("/user/{id}")
	public Response deleteUser(@PathParam("id") int id) {
		int numRowsAffected;
		ResponseBuilder respBuilder;
		
		numRowsAffected = userService.removeUserWithId(id);
		if (numRowsAffected == 0) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.ok();
		}
		return respBuilder.build();
	}
}
