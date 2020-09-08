package com.social.webapp.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.social.webapp.Others.EncryptAndDecrypt;
import com.social.webapp.entity.AddedFriends;
import com.social.webapp.entity.FriendRequest;
import com.social.webapp.entity.PostDetails;
import com.social.webapp.entity.UserDetails;
import com.social.webapp.model.GetFriendRequest;
import com.social.webapp.model.LoginRequestModel;
import com.social.webapp.model.LoginResponseModel;
import com.social.webapp.model.UserDetailsResponse;
import com.social.webapp.repocitory.AddedFriendsRepocitory;
import com.social.webapp.repocitory.FriendRequestRepository;
import com.social.webapp.repocitory.PostDetailRepository;
import com.social.webapp.repocitory.UserDetailsRepocitory;

@Service
public class UsersDataService {
	@Autowired
	UserDetailsRepocitory userdetailrepository;
	
	@Autowired
	PostDetailRepository postdetailrepository;
	@Autowired ServletContext context;
	@Autowired FriendRequestRepository friendrequestrepoitory;
	@Autowired AddedFriendsRepocitory addedfriendsrepository;
	

	public LoginResponseModel userlogin(LoginRequestModel loginrequestmethod) {
		
		boolean userexist;
		String uid,username,profilepic,email,mobilenumber,token;
		UserRecord userrecord;
//		String mmm=loginrequestmethod.getToken();
		
		// below if for testing...
//		if(loginrequestmethod.getToken().equals(mmm)){
//			UserDetails userdetails = new UserDetails();
//			Date currentdate = new Date(Calendar.getInstance().getTime().getTime());
//			java.sql.Time currenttime = new java.sql.Time(Calendar.getInstance().getTimeInMillis());
//			userdetails.setCreateddate(currentdate);
//			userdetails.setCreatedtime(currenttime);
//			userdetails.setFirebaseuid(mmm);
//			userdetails.setEmail("sdfsdfsd");
//			userdetails.setDelete(0);
//			userdetails.setMobilenumber("97687574");
//			UserDetails newuser=userdetailrepository.save(userdetails);
////			UserDetails newuser=userdetailrepository.findByFirebaseuid(mmm);
//			
//			return new LoginResponseModel("1","Login Success",newuser.getCreateddate().toString());
//			}
		
		
		
		if(loginrequestmethod.getToken()==null&&TextUtils.isEmpty(loginrequestmethod.getToken())&&loginrequestmethod.getToken().equals("")) {
			return new LoginResponseModel("0","Token Missing","Not Available");
		}
		try {
			token = loginrequestmethod.getToken();
			FirebaseToken firebasetoken = FirebaseAuth.getInstance().verifyIdToken(token);
			uid = firebasetoken.getUid();
			userrecord=FirebaseAuth.getInstance().getUser(uid);
			username=userrecord.getDisplayName();
			profilepic=userrecord.getPhotoUrl();
			email=userrecord.getEmail();
			mobilenumber=userrecord.getPhoneNumber();
			userexist=userdetailrepository.existsByFirebaseuid(uid);
			System.out.print(userexist);
			
			if(!userexist) {
				System.out.print("new");
			Date currentdate = new Date(Calendar.getInstance().getTime().getTime());
			java.sql.Time currenttime = new java.sql.Time(Calendar.getInstance().getTimeInMillis());
			mobilenumber=mobilenumber!=null?mobilenumber:"null";
			email=email!=null?email:"null";
			uid=uid!=null?uid:"null";
			UserDetails userdetails = new UserDetails(mobilenumber,email,username,profilepic,uid,currentdate,currenttime,1);
			UserDetails newuser=userdetailrepository.save(userdetails);
//			UserDetails newuser=userdetailrepository.findByFirebaseuid(uid);
//			String resid=newuser.getFirebaseuid();
			String resid =EncryptAndDecrypt.encrypt(String.valueOf(newuser.getUserid()));
			System.out.print(EncryptAndDecrypt.encrypt(String.valueOf(newuser.getUserid())));
			return new LoginResponseModel("1","Login Success",resid);
			}else {
				System.out.print("exist");
				UserDetails existuser=userdetailrepository.findByFirebaseuid(uid);
//				String resid=existuser.getFirebaseuid();
				String resid =EncryptAndDecrypt.encrypt(String.valueOf(existuser.getUserid()));
				System.out.print(EncryptAndDecrypt.encrypt(String.valueOf(existuser.getUserid())));
				return new LoginResponseModel("1","Excisting user",resid);
			}
			
		}catch(Exception e) {
			
		}
		return new LoginResponseModel("0","Invalid Token","Not Available");
	}
	
	
	public LoginResponseModel postafile(String userid,String postmessage,MultipartFile files) {
		boolean userexist;
		String decrypt = EncryptAndDecrypt.decrypt(userid);
		long id =Long.valueOf(decrypt);
		Date currentdate = new Date(Calendar.getInstance().getTime().getTime());
		java.sql.Time currenttime = new java.sql.Time(Calendar.getInstance().getTimeInMillis());
		userexist = userdetailrepository.existsById(id);
		UserDetails userDetails = new UserDetails();
		
			boolean folderexists = new File(context.getRealPath("/postdatas/")).exists();
			if(!folderexists) {
				new File(context.getRealPath("/postdatas/")).mkdir();
			}
			
			String filename = files.getOriginalFilename();
			String modifiedfilename  = FilenameUtils.getBaseName(filename)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
			File serverfile =new File(context.getRealPath("/postdatas/"+File.separator+modifiedfilename));
			try {
				FileUtils.writeByteArrayToFile(serverfile,files.getBytes());
			}catch(Exception e) {
				
			}
		
		
		if(userexist) {
			try {
//				UserDetails userdd = userdetailrepository.findByUserid(id);
				
				String encoderBase64 = java.util.Base64.getEncoder().encodeToString(files.getBytes());
//				  images.add("data : "+files.getContentType()+";base64"+encoderBase64);
				  String data = "data:"+files.getContentType()+";base64,"+encoderBase64;
				PostDetails postDetails = new PostDetails(postmessage,currentdate,currenttime,data,modifiedfilename,files.getContentType(),files.getSize(),1);
			
//				userdd.setUserid(id);
//				userdd.postlist.add(postDetails);
//				
//				UserDetails userresp  = userdetailrepository.save(userdd);
//				if(userresp==null) {
//					return new LoginResponseModel("1","Failed","posted");
//				}
				postDetails.setUserdetailid(userdetailrepository.findByUserid(id));
				
				PostDetails saveddetail = postdetailrepository.save(postDetails);
				
				
//				System.out.println(files.getBytes());
//				System.out.println(saveddetail);
				return new LoginResponseModel("1","Succefully","posted");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			return new LoginResponseModel("0","Invalid Token","Not Available");
		}
		return new LoginResponseModel("0","Invalid Token","Not Available");
	}
	
	
	public List<PostDetails> allposts(String userid) {
		
		/*
		 * List<String> images = new ArrayList<>() ; String filepath =
		 * context.getRealPath("/postdatas"); File filefolder =new File(filepath);
		 * if(filefolder!=null) { for(final File file :filefolder.listFiles()) {
		 * if(file.isDirectory()) { String encoderBase64 = null; try { String extension=
		 * FilenameUtils.getExtension(file.getName()); FileInputStream fileInputStream =
		 * new FileInputStream(file); byte[] bytes = new byte[(int)file.length() ];
		 * fileInputStream.read(bytes); encoderBase64
		 * =java.util.Base64.getEncoder().encodeToString(bytes);
		 * images.add("data : image/"+extension+";base64"+encoderBase64);
		 * fileInputStream.close(); }catch(Exception e) {
		 * 
		 * } } } }
		 */
		if(check(userid)!=0) {
			List<PostDetails> getallposts = postdetailrepository.findByDeleteOrderByPostdateDescPosttimeDesc(1);
			return getallposts;
		}
		 
		
		
//		List<UserDetails> getallposts = userdetailrepository.findAll();
		
		
//		System.out.print(getallposts.size());
//		System.out.print(getallposts.get(0).getUserid());
		return new ArrayList<>();
	
	}
	
	
	public LoginResponseModel checkuser(String userid) {
		
		String encrypteid = EncryptAndDecrypt.decrypt(userid);
		long id = Long.valueOf(encrypteid);
		boolean userexist=userdetailrepository.existsByUserid(id);
		
		if(userexist) {
		 return new LoginResponseModel("1","Excisting user","verifies");
		}
		
		return new LoginResponseModel("0","Invalid Token", "Not Available");
		
		
	}
	
	
	public UserDetailsResponse getprofiledetail(String userid) {
		String encrypteid = EncryptAndDecrypt.decrypt(userid);
		System.out.println(encrypteid);
		long id = Long.valueOf(encrypteid);
		System.out.println(id);
		boolean userexist=userdetailrepository.existsByUserid(id);
		System.out.println(userexist);
		if (userexist) {
		UserDetails getdetail = userdetailrepository.findByUserid(id);
		System.out.println(getdetail.getEmail());
		return new UserDetailsResponse("1",getdetail.getProfilename()==null?"null":getdetail.getProfilename(), getdetail.getEmail()==null?"null":getdetail.getEmail(), getdetail.getMobilenumber()==null?"null":getdetail.getMobilenumber(), getdetail.getProfileimage()==null?"null":getdetail.getProfileimage());                 
		
		}else {
			return new UserDetailsResponse("0","null","null","null","null");
		}
		
	}
	
	public List<UserDetails> allusers(String users){
		
		if(check(users)!=0) {
			List<AddedFriends> adedfriend = addedfriendsrepository.findByAccepterid(check(users));
			ArrayList<Long> sendid = new ArrayList<>(); 
			
			for(AddedFriends requ : adedfriend) {
				
				sendid.add(requ.getSenderid());
				System.out.println("comming in sid e"+sendid.size());
			}
			List<UserDetails> sortedlist = userdetailrepository.findAllById(sendid);
			
			/*
			 * List<UserDetails> userdetails = userdetailrepository.findAll(); UserDetails
			 * detail = userdetailrepository.findByUserid(check(users));
			 * userdetails.remove(detail);
			 */
			return sortedlist;
		}
		return new ArrayList<UserDetails>();
	}
	
	
public List<UserDetails> friendreqstuser(String users){
		
		if(check(users)!=0) {
			List<UserDetails> userdetails = userdetailrepository.findAll();
			long ii = check(users);
			UserDetails detail = userdetailrepository.findByUserid(check(users));
			userdetails.remove(detail);
			System.out.println("get the userdetaail one "+userdetails.size());
			List<FriendRequest> senderdetaisl = friendrequestrepoitory.findAllBySenderidAndDeleteGreaterThanEqual(check(users),"1");
			ArrayList<Long> sendid = new ArrayList<>(); 
			for(FriendRequest requ : senderdetaisl) {

				
				sendid.add(requ.getGeterid());
				
				System.out.println("comming in side----"+requ.getGeterid());
			}
			
			userdetails.removeAll(userdetailrepository.findAllById(sendid));
			
			
			List<FriendRequest> acceplist = friendrequestrepoitory.findAllByGeteridAndDelete(check(users),"2");
			ArrayList<Long> acptid = new ArrayList<>(); 
			for(FriendRequest ddd : acceplist) {
				acptid.add(ddd.getSenderid());
			}
			
			userdetails.removeAll(userdetailrepository.findAllById(acptid));
//			List<UserDetails> sortedlist = 
//			System.out.println("get the selected one "+sortedlist.size());
			
			System.out.println("get the userdetaail 2 "+userdetails.size());
			return userdetails;
			
		}
		return new ArrayList<UserDetails>();
	}
	
	
	public LoginResponseModel updateprofileimage(String userid,MultipartFile files) {
		
		try {
		if(check(userid)!=0) {
			UserDetails detail = userdetailrepository.findByUserid(check(userid));
			
			String encoderBase64 = java.util.Base64.getEncoder().encodeToString(files.getBytes());
//			  images.add("data : "+files.getContentType()+";base64"+encoderBase64);
			  String data = "data:"+files.getContentType()+";base64,"+encoderBase64;
			
			detail.setProfileimage(data);
			UserDetails savedate = userdetailrepository.save(detail);
			return new LoginResponseModel("1","sucessfull ",savedate.getProfileimage());
			
		}else {
			return new LoginResponseModel("0","invalid token ","not Available");
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return new LoginResponseModel("0","invalid token ","not Available");
	
	}
	
	
	public LoginResponseModel sendrequest(@RequestBody GetFriendRequest getfriendrequest) {
		
		String userid = getfriendrequest.getUserid();
		long toid = getfriendrequest.getToid();
//		System.out.println(toid);
		if(check(userid)!=0) {
			Date currentdate = new Date(Calendar.getInstance().getTime().getTime());
			java.sql.Time currenttime = new java.sql.Time(Calendar.getInstance().getTimeInMillis());
			
		UserDetails userdetails = userdetailrepository.findByUserid(check(userid));
		FriendRequest friendrequest = new FriendRequest(check(userid), toid, currentdate, currenttime, "1", userdetails);
		FriendRequest saveresponse = friendrequestrepoitory.save(friendrequest);
		if(saveresponse!=null) {
				return new LoginResponseModel("1", "successfull", userid);
		}else {
			return new LoginResponseModel("0","Failed ","not Available");
		}
		
		}
		return new LoginResponseModel("0","invalid token ","not Available");
		
	}
	
	
	public List<FriendRequest> getrequest(String userid){
		if(check(userid)!=0) {
			List <FriendRequest> getfriends = friendrequestrepoitory.findAllByGeteridAndDelete(check(userid),"1");
			
//			Specification<List<FriendRequest>> friendspecifier = FriendRequestRepository.findAll();
			
			System.out.println(getfriends.size());
			return getfriends;
		}
		return new ArrayList<>();
	}
	
	public LoginResponseModel deletRequest(GetFriendRequest getfriendrequest) {
		
		String userid = getfriendrequest.getUserid();
		long requestid = getfriendrequest.getToid();
		
		if(check(userid)!=0) {
			
			FriendRequest getrequest = friendrequestrepoitory.findByRequestid(requestid);
			getrequest.setDelete("0");
			FriendRequest savereqest = friendrequestrepoitory.save(getrequest);
			return new LoginResponseModel("1", "deleted", userid);
		}
		return new LoginResponseModel("0", "not error", userid);
	}
	
	
	public LoginResponseModel acceptFriends(GetFriendRequest getFriendRequest) {
		String userid = getFriendRequest.getUserid();
		long senderid = getFriendRequest.getSendid();
		long requestid=getFriendRequest.getToid();
		
		if(check(userid)!=0) {
			Date currentdate = new Date(Calendar.getInstance().getTime().getTime());
			java.sql.Time currenttime = new java.sql.Time(Calendar.getInstance().getTimeInMillis());
			
			AddedFriends add = new AddedFriends(check(userid), senderid, currentdate, currenttime, 1);
			AddedFriends add2 = new AddedFriends(senderid, check(userid), currentdate, currenttime, 1);
			AddedFriends save = addedfriendsrepository.save(add);
			AddedFriends save2 = addedfriendsrepository.save(add2);
			if(save!=null) {
				FriendRequest getdelete=friendrequestrepoitory.findByRequestid(requestid);
				getdelete.setDelete("2");
				FriendRequest savedfriend = friendrequestrepoitory.save(getdelete);
				if(savedfriend!=null) {
					return new LoginResponseModel("1", "deleted", userid);
				}
			}
			return new LoginResponseModel("0", "saved", userid);
		}
		
		return new LoginResponseModel("0","Somthing went wrong","null");
	}
	
	
	public LoginResponseModel deletePost(GetFriendRequest getFriendRequest) {
		String userid = getFriendRequest.getUserid();
		long senderid = getFriendRequest.getSendid();
		long requestid=getFriendRequest.getToid();
		
		if(check(userid)!=0) {
			PostDetails postDetails = postdetailrepository.findByPostid(requestid);
			postDetails.setDelete(0);
			PostDetails savepost = postdetailrepository.save(postDetails);
			return new LoginResponseModel("1", "deleted", userid);
		}
		
		return new LoginResponseModel("0","Somthing went wrong","null");
	}
	
	
	public long check(String userid) {

		
//		System.out.println(userid);
		String encrypteid = EncryptAndDecrypt.decrypt(userid);
//		System.out.println(encrypteid);
		long id = Long.valueOf(encrypteid);
//		System.out.println(id);
		boolean exist = userdetailrepository.existsByUserid(id); 
		if(exist) {
			return id;
		}else {
			return 0;
		}
		
		
	}
	
	public List<UserDetails> adminallusers(){
		return userdetailrepository.findAll();
	}
	
	public  LoginResponseModel changeusername(String userid,String changetext) {
		
		if(check(userid)!=0) {
			UserDetails detail = userdetailrepository.findByUserid(check(userid));
		
			
			detail.setProfilename(changetext);
			UserDetails savedate = userdetailrepository.save(detail);
			return new LoginResponseModel("1","sucessfull ",savedate.getProfilename());
		}
		return new LoginResponseModel("0","FAiled ","not");
	}
	
}
