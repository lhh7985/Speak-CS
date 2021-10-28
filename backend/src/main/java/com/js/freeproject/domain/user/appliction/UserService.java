package com.js.freeproject.domain.user.appliction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;
import com.js.freeproject.domain.user.dto.UserRequest;
import com.sun.jdi.request.DuplicateRequestException;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public User createUser(UserRequest user) {
		User finduser = userRepo.findByEmail(user.getEmail());
		if(finduser != null) {
			throw new DuplicateRequestException(user.getEmail());
		}
		
		User userEntity = User.builder()
				.email(user.getEmail())
				.nickName(user.getNickName())
				.name(user.getName())
				.nickName(user.getNickName())
				.pass(user.getPass())
				.image(user.getImage())
				.build();
		
		return userRepo.save(userEntity);
	}
	
	public User findByUserEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public boolean findByUserNickName(String nickName) {
		return userRepo.existsByNickName(nickName);
	}
}
