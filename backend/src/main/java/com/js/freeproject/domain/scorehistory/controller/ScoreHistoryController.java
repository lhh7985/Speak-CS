package com.js.freeproject.domain.scorehistory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.model.CommonResponse;
import com.js.freeproject.domain.scorehistory.application.ScoreHistoryService;
import com.js.freeproject.domain.scorehistory.domain.ScoreHistory;
import com.js.freeproject.domain.user.application.UserService;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.dto.UserResponse;
import com.js.freeproject.global.jwt.CustomUserDetails;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/score")
public class ScoreHistoryController {
	private final ScoreHistoryService scoreHistoryService;
	private final UserService userService;
	
	@GetMapping()
	@ApiOperation(value = "전체 점수 가져오기")
	@ApiResponses({
		@ApiResponse(code=200,message="성공",response = UserResponse.class),
		@ApiResponse(code=500,message="서버 오류",response = CommonResponse.class)
	})
	public ResponseEntity<?> getScore(Authentication authentication) {
		CustomUserDetails accountDetailes = (CustomUserDetails) authentication.getDetails();
		
		User user = userService.findByUserEmail(accountDetailes.getUseremail());
		
		List<ScoreHistory> list = scoreHistoryService.getScore(user);
		
		System.out.println(list.toString());
		
		return ResponseEntity.status(200).body(CommonResponse.of("Success"));
	}
	
	@GetMapping("{category_id}")
	@ApiOperation(value = "전체 점수 가져오기")
	@ApiResponses({
		@ApiResponse(code=200,message="성공",response = UserResponse.class),
		@ApiResponse(code=500,message="서버 오류",response = CommonResponse.class)
	})
	public ResponseEntity<?> getCategoryScore(Authentication authentication, @PathVariable Long category_id) {
		CustomUserDetails accountDetailes = (CustomUserDetails) authentication.getDetails();
		
		User user = userService.findByUserEmail(accountDetailes.getUseremail());
		Category category = new Category();
		category.setId(category_id);
		
		List<ScoreHistory> list = scoreHistoryService.getScoreCategory(user, category);
		
		System.out.println(list.toString());
		
		return ResponseEntity.status(200).body(CommonResponse.of("Success"));
	}
}
