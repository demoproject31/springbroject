package com.social.webapp.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.social.webapp.Others.EncryptAndDecrypt;
import com.social.webapp.entity.FriendRequest;
import com.social.webapp.entity.PostDetails;
import com.social.webapp.entity.UserDetails;
import com.social.webapp.model.GetFriendRequest;
import com.social.webapp.model.LoginRequestModel;
import com.social.webapp.model.LoginResponseModel;
import com.social.webapp.model.UserDetailsResponse;
import com.social.webapp.service.UsersDataService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SocialMainController {
	
	@Autowired
	UsersDataService userdataservise;
	

	@PostMapping("/normal/login")
	@CrossOrigin("http://localhost:4200")
	public LoginResponseModel loginmethod(@RequestBody LoginRequestModel loginrequestmodel) throws Exception{
		return userdataservise.userlogin(loginrequestmodel);
	}
	
	
	@PostMapping("/normal/newpost")
	@CrossOrigin("http://localhost:4200")
	public LoginResponseModel postnew(@RequestParam("userid")String userid,@RequestParam("postmessage") String postmessage, @RequestParam("files") MultipartFile files) {
		
		return userdataservise.postafile(userid, postmessage, files);
	}
	
	
	
	@PostMapping("/normal/getallpost")
	@CrossOrigin("http://localhost:4200")
	public List<PostDetails> getallposts(@RequestParam("userid")String userid) {
		return userdataservise.allposts(userid);
	}
	
	
	@PostMapping("/normal/checkuser")
	@CrossOrigin("http://localhost:4200")
	public LoginResponseModel checkuser(@RequestParam("userid") String userid) {
		return userdataservise.checkuser(userid);
	}
	
	
	@PostMapping("/normal/getprofiledetails")
	@CrossOrigin("http://localhost:4200")
	public UserDetailsResponse getprofile(@RequestParam("userid")String userid) {
//		System.out.println(userid);
		return userdataservise.getprofiledetail(userid);
	}  
	
	
	@PostMapping("/normal/getallusers")
	@CrossOrigin("http://localhost:4200")
	public List<UserDetails> getallusers(@RequestParam("userid")String userid){
		return userdataservise.allusers(userid);
	}
	
	@PostMapping("/normal/uploadprofileimg")
	@CrossOrigin("http://localhost:4200")
	public LoginResponseModel updateprofileimage(@RequestParam("userid")String userid,@RequestParam("profileimage") MultipartFile files) {
		return userdataservise.updateprofileimage(userid,files);
	}
	
	@PostMapping("/normal/sendfriend")
	@CrossOrigin("http://localhost:4200")
	public List<UserDetails> sendfriend(@RequestParam("userid")String userid){
		return userdataservise.friendreqstuser(userid);
	}
	
	@PostMapping("/normal/sendrequest")
	@CrossOrigin("http://localhost:4200")
	public LoginResponseModel sendRequest(@RequestBody GetFriendRequest getfriendrequest) {
		
		return userdataservise.sendrequest(getfriendrequest);
	}
	
	@PostMapping("/normal/requestedfriend")
	@CrossOrigin("http://localhost:4200")
	public List<FriendRequest> getfriendrequest(@RequestParam("userid")String userid){
		
		return userdataservise.getrequest(userid);
	}
	
	@PostMapping("/normal/deleterequest")
	@CrossOrigin("http://localhost:4200")
	public LoginResponseModel deleteRequest(@RequestBody GetFriendRequest getfriendrequest) {
//		System.out.println("455263--"+getfriendrequest.getToid());
		return userdataservise.deletRequest(getfriendrequest);
	}
	
	
	@PostMapping("/normal/acceptfriends")
	@CrossOrigin("http://localhost:4200")
	public LoginResponseModel acceptFriends(@RequestBody GetFriendRequest getfriendrequest) {
//		System.out.println("455263--"+getfriendrequest.getToid());
		return userdataservise.acceptFriends(getfriendrequest);
	}
	
	@PostMapping("/normal/deletePost")
	@CrossOrigin("http://localhost:4200")
	public LoginResponseModel deletePost(@RequestBody GetFriendRequest getfriendrequest) {
//		System.out.println("455263--"+getfriendrequest.getToid());
		return userdataservise.deletePost(getfriendrequest);
	}
	
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/admin/demo")
	public List<UserDetails> adminalluser(){
		return userdataservise.adminallusers();
		
	}
	
	@PostMapping("/normal/changeusername")
	@CrossOrigin("http://localhost:4200")
	public LoginResponseModel changename(@RequestParam("userid")String userid,@RequestParam("changetext")String changetext) {
		
		return userdataservise.changeusername(userid, changetext);
	}
	
	
	
}
