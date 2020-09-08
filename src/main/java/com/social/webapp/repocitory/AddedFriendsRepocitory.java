package com.social.webapp.repocitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.webapp.entity.AddedFriends;

public interface AddedFriendsRepocitory extends JpaRepository<AddedFriends, Long>{
	
	List<AddedFriends> findByAccepterid(long userid);

}
