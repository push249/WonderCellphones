package com.webmobilecart.mobileapp.domain;
import java.io.Serializable;





import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.annotation.DateTimeFormat;


public class User implements Serializable  {
	String firstName;
	String lastName;
	String userName;
	String password;
	String eMail;
	String address;
	String contactNo;
	int idUser;
	public int getIdUser(){
		return idUser;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public boolean equals(Object obj){
		User user = (User)obj;
		if(eMail.equals(user.eMail)&&password.equals(user.password)&&userName.equals(user.userName)){
			return true;
		}
		else {
			return false;
		}
	}
}
