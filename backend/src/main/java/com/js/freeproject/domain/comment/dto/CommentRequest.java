package com.js.freeproject.domain.comment.dto;

import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CommentRequest {
    private String content;
    private Long userId;
    private Long parentId;

}
