package com.social.webapp.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "friendrequest")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendRequest implements Serializable{
	
	private static final long serialVersionUID = -7302800336276816169L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long requestid;
	
	@Column(name = "senderid")
	long senderid;
	@Column(name = "geterid")
	long geterid;
	@Column(name = "senddate")
	Date senddate;
	@Column(name="sendtime")
	Time sendTime;
	@Column(name = "delete")
	String delete;
	
	@ManyToOne(cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "requserid",referencedColumnName = "userid")
//	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	public UserDetails requestdetails;
	
	public FriendRequest(long senderid, long geterid, Date senddate, Time sendTime, String delete,UserDetails requestdetails) {
		this.requestdetails=requestdetails;
		this.senderid = senderid;
		this.geterid = geterid;
		this.senddate = senddate;
		this.sendTime = sendTime;
		this.delete = delete;
	}
	public FriendRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getRequestid() {
		return requestid;
	}
	public void setRequestid(long requestid) {
		this.requestid = requestid;
	}
	public long getSenderid() {
		return senderid;
	}
	public void setSenderid(long senderid) {
		this.senderid = senderid;
	}
	public long getGeterid() {
		return geterid;
	}
	public void setGeterid(long geterid) {
		this.geterid = geterid;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	public Time getSendTime() {
		return sendTime;
	}
	public void setSendTime(Time sendTime) {
		this.sendTime = sendTime;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}

	

}
