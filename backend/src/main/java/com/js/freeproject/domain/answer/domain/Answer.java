package com.js.freeproject.domain.answer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.js.freeproject.domain.problem.domain.Problem;

import lombok.Getter;

@Entity
@Getter
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String word;
	
	@ManyToOne
	@JoinColumn(name="problem_id")
	private Problem problem;
}
