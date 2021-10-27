package com.js.freeproject.domain.board.dto;

import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardSaveResponse {
    private Long id;
    private String title;
    private String description;
    private User user;

    public BoardSaveResponse(final Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.user = board.getUser();
    }
}
