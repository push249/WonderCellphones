package com.webmobilecart.mobileapp.exceptions;

public class DbFailure extends Exception {
	public DbFailure(){
		super();
	}
	public DbFailure(String str){
		super(str);
	}
}
