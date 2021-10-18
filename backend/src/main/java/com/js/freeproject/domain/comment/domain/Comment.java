package com.js.freeproject.domain.comment.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.js.freeproject.domain.board.domain.Board;

import lombok.Getter;

@Entity
@Getter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="board_id")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Comment comment;
	
	@OneToMany(mappedBy = "comment")
	private List<Comment> comments;
}
