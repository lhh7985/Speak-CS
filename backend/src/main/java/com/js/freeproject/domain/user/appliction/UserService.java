package com.js.freeproject.domain.user.appliction;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;
import com.js.freeproject.domain.user.dto.UserRequest;
import com.sun.jdi.request.DuplicateRequestException;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepo;
	
	@Transactional
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
	
	@Transactional
	public User modifyUser(UserRequest userRequest) throws NotFoundException {
		User user = userRepo.findByEmail(userRequest.getEmail());
		if(user==null) {
			throw new NotFoundException(userRequest.getEmail() + "를 찾을 수 없습니다.");
		}
		
		return user.builder()
				.nickName(userRequest.getNickName())
				.pass(userRequest.getPass())
				.image(userRequest.getImage())
				.build();
	}
}
