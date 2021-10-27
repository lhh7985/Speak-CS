package com.js.freeproject.domain.board.dto;

import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.boardpicture.domain.BoardPicture;
import com.js.freeproject.domain.file.domain.BoardFile;
import com.js.freeproject.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardListResponse {
    private Long id;
    private String title;
    private String description;
    private User user;
    private LocalDateTime createdAt;
    private List<BoardFile> boardFiles;

    public static BoardListResponse ofBoard(final Board board){
        return new BoardListResponse(board);
    }

    private BoardListResponse(final Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.boardFiles = board.getBoardFiles();
        this.user = board.getUser();
        this.createdAt = board.getCreateDate();
    }
}
