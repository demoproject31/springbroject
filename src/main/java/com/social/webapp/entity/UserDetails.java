package com.social.webapp.entity;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;



import com.sun.istack.Nullable;



@Entity
@Table(name = "userdetails")
public class UserDetails  implements Serializable{
	
	private static final long serialVersionUID = -7302800336276816169L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 long userid;
	@Column(name = "mobilenumber",nullable = false)
	@NotNull
	 String mobilenumber;
	@Column(name = "email",nullable =false )
	@Nullable
	 String email;
	@Column(name = "profilename",nullable = true)
	@Nullable
	 String profilename;
	@Column(name = "profileimage",nullable = true,length = 1000000000)
	@Nullable
	 String profileimage;
	@Column(name = "firebaseuid",nullable = false)
	@NotNull
	 String firebaseuid;
	
	@Column(name = "createddate",nullable = false)
	@NotNull
	 Date createddate;
	
	
	@Column(name = "createdtime",nullable = false)
	@NotNull
	 Time createdtime;
	@Column(name = "delete",nullable = true)
	@Nullable
	int delete;
	
	@OneToMany(targetEntity = PostDetails.class,mappedBy = "userdetails",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	 List<PostDetails> postlist = new ArrayList<PostDetails>();
	
	@OneToMany(targetEntity = FriendRequest.class,mappedBy = "requestdetails",cascade = CascadeType.ALL)
	 private List<FriendRequest> requests = new ArrayList();

	public UserDetails() {
		
		// TODO Auto-generated constructor stub
		
	}
	public UserDetails(@NotNull String mobilenumber, String email, String profilename, String profileimage,
			@NotNull String firebaseuid, @NotNull Date createddate, @NotNull Time createdtime, int delete) {
		
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.profilename = profilename;
		this.profileimage = profileimage;
		this.firebaseuid = firebaseuid;
		this.createddate = createddate;
		this.createdtime = createdtime;
		this.delete = delete;
	}

	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfilename() {
		return profilename;
	}
	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}
	public String getProfileimage() {
		return profileimage;
	}
	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
	public String getFirebaseuid() {
		return firebaseuid;
	}
	public void setFirebaseuid(String firebaseuid) {
		this.firebaseuid = firebaseuid;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public Time getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(Time createdtime) {
		this.createdtime = createdtime;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	private java.util.List<PostDetails> getPostlist() {
		return postlist;
	}
	public void setPostlist(java.util.List<PostDetails> postlist) {
		this.postlist = postlist;
	}
	private List<FriendRequest> getRequests() {
		return requests;
	}
	public void setRequests(List<FriendRequest> requests) {
		this.requests = requests;
	}


}
