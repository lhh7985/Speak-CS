package com.js.freeproject.domain.boardpicture.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.js.freeproject.domain.board.domain.Board;

import lombok.Getter;

@Entity
@Getter
public class BoardPicture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String image;
	
	@ManyToOne
	@JoinColumn(name="board_id")
	private Board board;
}
