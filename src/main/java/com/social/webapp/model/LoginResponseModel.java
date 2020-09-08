package com.social.webapp.model;
import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
public class LoginResponseModel implements Serializable{


	String status ;
	String message;
	String userid;
	
	public LoginResponseModel() {
		
	}
	public LoginResponseModel(String status, String message, String userid) {
		super();
		this.status = status;
		this.message = message;
		this.userid = userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	
}
