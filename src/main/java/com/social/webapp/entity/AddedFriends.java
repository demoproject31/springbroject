package com.social.webapp.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "addedfriends")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddedFriends {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long friendid;
	
	@Column(name = "accepterid")
	private long accepterid;

	@Column(name = "senderid")
	private long senderid;
	
	@Column(name = "accepteddate")
	private Date accepteddata;
	
	@Column (name = "acceptedtime")
	private Time acceptedtime;
	
	@Column(name = "delete")
	private int delete;

	public AddedFriends(long accepterid, long senderid, Date accepteddata, Time acceptedtime, int delete) {
		super();
		this.accepterid = accepterid;
		this.senderid = senderid;
		this.accepteddata = accepteddata;
		this.acceptedtime = acceptedtime;
		this.delete = delete;
	}

	public AddedFriends() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getFriendid() {
		return friendid;
	}

	public void setFriendid(long friendid) {
		this.friendid = friendid;
	}

	public long getAccepterid() {
		return accepterid;
	}

	public void setAccepterid(long accepterid) {
		this.accepterid = accepterid;
	}

	public long getSenderid() {
		return senderid;
	}

	public void setSenderid(long senderid) {
		this.senderid = senderid;
	}

	public Date getAccepteddata() {
		return accepteddata;
	}

	public void setAccepteddata(Date accepteddata) {
		this.accepteddata = accepteddata;
	}

	public Time getAcceptedtime() {
		return acceptedtime;
	}

	public void setAcceptedtime(Time acceptedtime) {
		this.acceptedtime = acceptedtime;
	}

	public int getDelete() {
		return delete;
	}

	public void setDelete(int delete) {
		this.delete = delete;
	}


	
}
