package com.js.freeproject.domain.board.dto;

import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.boardpicture.domain.BoardPicture;
import com.js.freeproject.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponse {
    private Long id;
    private String title;
    private String description;
    private User user;
    private List<BoardPicture> boardPictures;

    public BoardResponse(final Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.boardPictures = getBoardPictures();
        this.user = board.getUser();
    }
}
