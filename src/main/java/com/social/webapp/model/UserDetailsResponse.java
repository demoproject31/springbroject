package com.social.webapp.model;

import org.springframework.stereotype.Component;

@Component
public class UserDetailsResponse {
	
	private String status;
	private String username;
	private String email;
	private String number;
	private String imageurl;
	
	
	public UserDetailsResponse(String status,String username, String email, String number, String imageurl) {
		this.status=status;
		this.username = username;
		this.email = email;
		this.number = number;
		this.imageurl = imageurl;
	}


	public UserDetailsResponse() {
		
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getImageurl() {
		return imageurl;
	}


	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	
	
	
}
