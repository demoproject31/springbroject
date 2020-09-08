package com.social.webapp.model;

import org.springframework.stereotype.Component;

@Component
public class AllUsersResponse {

	
	String status;
	long userid;
	String imagedata;
	String username;
	String available;
	public AllUsersResponse(String status, long userid, String imagedata, String username, String available) {
		super();
		this.status = status;
		this.userid = userid;
		this.imagedata = imagedata;
		this.username = username;
		this.available = available;
	}
	public AllUsersResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getImagedata() {
		return imagedata;
	}
	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	
	
	
}
