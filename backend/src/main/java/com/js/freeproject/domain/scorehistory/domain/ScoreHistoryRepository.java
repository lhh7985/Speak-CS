package com.js.freeproject.domain.scorehistory.domain;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.user.domain.User;

public interface ScoreHistoryRepository extends JpaRepository<ScoreHistory, Long> {

	@EntityGraph(attributePaths = {"user","category"})
	List<ScoreHistory> findByUser(User user);
	
	@EntityGraph(attributePaths = {"user","category"})
	List<ScoreHistory> findByCategoryAndUser(Category category,User user);
	
}
