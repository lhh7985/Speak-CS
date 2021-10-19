package com.js.freeproject.domain.problempicture.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.js.freeproject.domain.problem.domain.Problem;

import lombok.Getter;

@Entity
@Getter
public class ProblemPicture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String image;
	
	@OneToOne
	@JoinColumn(name="problem_id")
	private Problem problem;
}
