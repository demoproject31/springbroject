package com.social.webapp.repocitory;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.social.webapp.entity.PostDetails;
import com.social.webapp.entity.UserDetails;

public interface UserDetailsRepocitory extends JpaRepository<UserDetails, Long>{

	boolean existsByMobilenumber(String mobilenumber);
	boolean existsByFirebaseuid(String userid);
	boolean existsByUserid(long userid);
	UserDetails findByFirebaseuid(String uid);
	UserDetails findByUserid(long id);
	
	
//	@Modifying
//	@Query(value = "select * from userdetails where userid in (1?)")
//	List<UserDetails> findAllByUserid(List<Long> ids);
	
//	List<UserDetails> findByUserid(List<Long> ids);
	
//	@Modifying
//	@Query("update userdetails d set d.user_fid=?1 where d.userid=?2")
////	public int saveByPostlist(List<PostDetails> user_fid ,long userid);
//	
	
//	List<UserDetails> findAllOrderByCreateddateAsc();
	
	
//	List<UserDetails> findAll
}
