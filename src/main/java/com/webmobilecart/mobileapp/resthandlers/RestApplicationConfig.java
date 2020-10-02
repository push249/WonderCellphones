package com.webmobilecart.mobileapp.resthandlers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.webmobilecart.mobileapp.exceptions.*;



@ApplicationPath("/")
public class RestApplicationConfig extends Application {
	private Set<Class<?>> restClassSet = new HashSet<Class<?>>();
	
	public RestApplicationConfig() {
		restClassSet.add(JacksonFeature.class);
		restClassSet.add(UserRestHandler.class);
		restClassSet.add(DbFailure.class);
	}
	
	public Set<Class<?>> getClasses() {
		return restClassSet;
	}
}
