package com.js.freeproject.domain.board.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
	private Date createDate;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	private List<BoardPicture> boardPictures;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	private List<File> files;
}
