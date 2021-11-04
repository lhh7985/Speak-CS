package com.js.freeproject.domain.scorehistory.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class ScoreHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer score;
	private Date createdate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
	
	@Builder
	public ScoreHistory(User user, Category category, Integer score, Date createdata) {
		this.user = user;
		this.category = category;
		this.score = score;
		this.createdate = new Date();
	}
}
