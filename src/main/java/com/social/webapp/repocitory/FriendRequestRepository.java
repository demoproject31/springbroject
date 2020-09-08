package com.social.webapp.repocitory;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.social.webapp.entity.FriendRequest;
import com.social.webapp.entity.UserDetails;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

//	List<UserDetails> findByRequserid(long userid);
	long[] findAllGeteridBySenderid(long id);
	
//	@Query(value = "select geterid from friendrequest where requserid=?1")
	public long[] findGeteridBySenderid(long id);
	
	List<FriendRequest> findAllBySenderidAndDelete(long id,String i);

	List<FriendRequest> findAllBySenderidAndDeleteGreaterThanEqual(long id,String i);
	@Modifying
	@Query(value = "SELECT * FROM newsocial.friendrequest  where friendrequest.delete=1? and friendrequest.geterid>=2?" )
	List<FriendRequest> findAllByGeteridAndDelete(Long userid,String i );
	
	@Modifying
	@Query(value = "SELECT * FROM newsocial.friendrequest  where friendrequest.delete=1? and friendrequest.geterid>=2?" )
	List<FriendRequest> findAllByGeteridAndDeleteGreaterThanEqual(Long userid,String i );
	
	List<FriendRequest> findAllByGeterid(long userid);
	
//	List<FriendRequest> findAllByGeteridAndDelete(Long userid,String i );
	
	FriendRequest findOneBySenderidAndGeterid(long sid,long gid);

	FriendRequest findByRequestid(long reqid);
	
	
//	@Transactional
	int  deleteByRequestid(long id);
	
}
