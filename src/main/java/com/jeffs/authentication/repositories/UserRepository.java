package com.jeffs.authentication.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeffs.authentication.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	//Get user by Email
	Optional<User> findByEmail(String search);
	//Get user by Id
	Optional<User> findById(Long search);

}
