package com.demo.taskboard.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.demo.taskboard.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findByUserSapId(String userSapId);

	Optional<User> findByUin(String uin);
	
	Optional<User> findByUserSapIdAndIsActive(String userSapId,Boolean active);

	//Optional<User> findByUserSapIdAndByRoles(String userSapId, String roleManager);

	Optional<User> findByUserSapIdAndRoles(String userCreatedBy, String roleManager);
}
