package com.js.freeproject.domain.user.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	public Optional<User> findByNickName(String nickName);
	public boolean existsByNickName(String nickName);
}
