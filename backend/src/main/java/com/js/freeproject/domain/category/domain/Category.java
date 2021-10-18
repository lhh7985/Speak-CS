package com.js.freeproject.domain.category.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.js.freeproject.domain.problem.domain.Problem;
import com.js.freeproject.domain.scorehistory.domain.ScoreHistory;

import lombok.Getter;

@Entity
@Getter
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Problem> problems = new ArrayList<Problem>();
	
	@OneToMany(mappedBy = "category")
	private List<ScoreHistory> scoreHistoryList;
}
