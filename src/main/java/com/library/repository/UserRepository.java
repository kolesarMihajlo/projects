package com.library.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.model.User;

public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {
//	update tbl_user set tbl_user.deleted =true where tbl_user.user_id=1;
//	@Transactional
	
	@Modifying(clearAutomatically=false)
	@Query("update User u set u.deleted = TRUE where u.id =:id")
	public void deleteLogical(@Param("id")Long id);
	
	public User findByUsername(String username);
	


}
