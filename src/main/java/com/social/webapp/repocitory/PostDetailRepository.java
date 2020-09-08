package com.social.webapp.repocitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.webapp.entity.PostDetails;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetails, Long>{

	
	PostDetails findByPostid(long userid);
	
	List<PostDetails> findByDeleteOrderByPostdateDescPosttimeDesc(int id);
}
