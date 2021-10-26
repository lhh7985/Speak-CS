package com.js.freeproject.domain.comment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;

	private LocalDateTime createdAt;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="board_id")
	private Board board;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="parent_id")
	private Comment parent;
	
//	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
//	private List<Comment> comments;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Comment(String content, Board board, Comment parent, User user) {
		this.content = content;
		this.board = board;
		this.parent = parent;
		this.user = user;
	}

	public Comment updateParent(final Comment comment){
		this.parent = comment;
		return this;
	}

	@PrePersist
	public void createdAt() {
		this.createdAt = LocalDateTime.now();
	}
}
