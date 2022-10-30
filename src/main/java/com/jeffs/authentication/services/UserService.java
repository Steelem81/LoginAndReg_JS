package com.jeffs.authentication.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jeffs.authentication.models.LoginUser;
import com.jeffs.authentication.models.User;
import com.jeffs.authentication.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		
		if(potentialUser.isPresent()) {
			result.rejectValue("email", "nonunique", "This email already exists");
		}
		
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Passwords do not match");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
			return userRepo.save(newUser);
		}
	}
	
	public User login(LoginUser newLoginObject, BindingResult result) {
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		if(!potentialUser.isPresent()){
			result.rejectValue("email", "notFound", "This email could not be found");
		} 
		User regUser = potentialUser.get();
		if(!BCrypt.checkpw(newLoginObject.getPassword(), regUser.getPassword())) {
			result.rejectValue("passwor", "matches", "Invalid Password");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			return regUser;
		}
				
	
	}
	
	public User getUserById(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if(potentialUser.isPresent()) {
			return potentialUser.get();
		} else {
			return null;
		}
	}

}
