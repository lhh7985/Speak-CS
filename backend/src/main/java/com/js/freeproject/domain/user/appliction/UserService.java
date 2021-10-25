package com.js.freeproject.domain.user.appliction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User createUser(User user) {
		User finduser = userRepo.findByEmail(user.getEmail());
		if(finduser != null) {
			throw new DuplicateRequestException(user.getEmail());
		}
		
		user.setPass(passwordEncoder.encode(user.getPass()));
		return userRepo.save(user);
	}
	
	public List<User> userList() {
		return userRepo.findAll();
	}
	
	public User findByUserEmail(String email) {
		return userRepo.findByEmail(email);
	}
}
