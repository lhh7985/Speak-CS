package com.js.freeproject.domain.board.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.js.freeproject.domain.boardpicture.domain.BoardPicture;
import com.js.freeproject.domain.comment.domain.Comment;
import com.js.freeproject.domain.file.domain.File;

import lombok.Getter;

@Entity
@Getter
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	private Date createdate;
	
	@OneToMany(mappedBy = "board")
	private List<BoardPicture> boardpictures;
	
	@OneToMany(mappedBy = "board")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "board")
	private List<File> files;
}
