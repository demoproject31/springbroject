package com.social.webapp.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.istack.Nullable;

@Entity
@Table(name = "postdetails")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDetails implements Serializable{
	private static final long serialVersionUID = -7302800336276816169L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long postid;
	
	
	
	@Column(name = "message")
	@Nullable
	public String message;
	
	@Column(name = "likecount",nullable = true)
	public String likescount;
	
	@Column(name = "sharecount",nullable = true)
	public String sharecount;
	
	@Column(name = "postdate",nullable = true)
	public Date postdate;
	
	@Column(name = "posttime",nullable = true)
	public Time posttime;
	
	@Column(name = "deletedate",nullable = true)
	public Date deletedate;
	
	@Column(name = "deletetime",nullable = true)
	public Time deletetime;
	
	@Column(name = "delete",nullable = true)
	public int delete;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name = "filedata",nullable = true,length = 1000000000)
	public String filedata;
	
	@Column(name = "originalname",nullable = true)
	public String originalname;
	
	@Column(name = "filetype",nullable=true)
	public String filetype;
	
	@Column (name = "filesize",nullable=true)
	public long filesize;
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = true)
	@JoinColumn(name = "userdetail_id",referencedColumnName = "userid")
//	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	public UserDetails userdetails;
	

	public PostDetails( String message, Date postdate, Time posttime,String filedata,
			String originalname, String filetype, long filesize,int delete) {
		super();
//		this.userdetailid=userdetailid;
		this.message = message;
		this.postdate = postdate;
		this.posttime = posttime;
		this.filedata = filedata;
		this.originalname = originalname;
		this.filetype = filetype;
		this.filesize = filesize;
		this.delete=delete;
	}

	public PostDetails() {
		
		// TODO Auto-generated constructor stub
	}

	public UserDetails getUserdetailid() {
		return userdetails;
	}

	public void setUserdetailid(UserDetails userdetailid) {
		this.userdetails = userdetailid;
	}

	public int getDelete() {
		return delete;
	}

	public void setDelete(int delete) {
		this.delete = delete;
	}
	
	
	
	
}
