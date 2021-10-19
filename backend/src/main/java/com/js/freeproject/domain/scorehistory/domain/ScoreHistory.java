package com.js.freeproject.domain.scorehistory.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.user.domain.User;

import lombok.Getter;

@Entity
@Getter
public class ScoreHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer score;
	private Date createdate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
}
