package com.js.freeproject.domain.comment.dto;

import com.js.freeproject.domain.comment.domain.Comment;
import com.js.freeproject.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {
    private Long id;
    private String content;
    private User user;
    private Long parentId;
    private LocalDateTime createdAt;

    public static final CommentResponse ofComment(final Comment comment){
        final Long id = comment.getId();
        final String content = comment.getContent();;
        final User user = comment.getUser();
        final LocalDateTime createdAt = comment.getCreatedAt();
        final Comment parent = comment.getParent();
        if(comment.getParent() != null)
            return new CommentResponse(id, content, user,createdAt, parent);
        return new CommentResponse(id, content, user,createdAt);
    }

    private CommentResponse(Long id, String content, User user,LocalDateTime createdAt, Comment parent) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.parentId = parent.getId();
        this.createdAt = createdAt;
    }

    private CommentResponse(Long id, String content, User user,LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}
