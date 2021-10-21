package com.js.freeproject.domain.user.appliction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	public List<User> userList() {
		return userRepo.findAll();
	}
}
