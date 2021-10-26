package com.js.freeproject.domain.comment.dto;

import com.js.freeproject.domain.comment.domain.Comment;
import com.js.freeproject.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {
    private Long id;
    private String content;
    private User user;
    private Long parentId;

    public static final CommentResponse ofComment(final Comment comment){
        final Long id = comment.getId();
        final String content = comment.getContent();;
        final User user = comment.getUser();
        final Comment parent = comment.getParent();
        if(comment.getParent() != null)
            return new CommentResponse(id, content, user, parent);
        return new CommentResponse(id, content, user);
    }

    private CommentResponse(Long id, String content, User user, Comment parent) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.parentId = parent.getId();
    }

    private CommentResponse(Long id, String content, User user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }
}
