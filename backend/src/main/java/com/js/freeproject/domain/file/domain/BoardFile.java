package com.js.freeproject.domain.file.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.js.freeproject.domain.board.domain.Board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="board_id")
	private Board board;

	@Builder
	public BoardFile(Long id, String name, Board board) {
		this.id = id;
		this.name = name;
		this.board = board;
	}

	public BoardFile updateBoardId(final Board board){
		this.board = board;
		return this;
	}

	public BoardFile updateFile(final String fileName){
		this.name = fileName;
		return this;
	}
}
