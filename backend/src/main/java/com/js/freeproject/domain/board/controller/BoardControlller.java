package com.js.freeproject.domain.board.controller;

import com.js.freeproject.domain.board.application.BoardService;
import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.board.dto.BoardListResponse;
import com.js.freeproject.domain.board.dto.BoardRequest;
import com.js.freeproject.domain.board.dto.BoardResponse;
import com.js.freeproject.domain.file.application.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardControlller {

    private final BoardService boardService;
    private final BoardFileService boardFileService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> saveQuestion(@RequestBody final BoardRequest boardRequest, @PathVariable Long userId) throws IOException {
        Board board = boardService.saveQuestion(boardRequest,userId);
        final BoardResponse boardResponse = new BoardResponse(board);
        return ResponseEntity.ok().body(boardResponse);
    }

    @GetMapping
    public ResponseEntity<?> getBoardList(){
        List<BoardListResponse> boardListResponses = boardService.findAllBoard();
        return ResponseEntity.ok().body(boardListResponses);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardOne(@PathVariable Long boardId){
        BoardResponse boardResponse = boardService.findById(boardId);
        return ResponseEntity.ok().body(boardResponse);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<?> putBoard(final BoardRequest boardRequest, @PathVariable Long boardId) throws IOException {
        boardService.updateDescription(boardRequest,boardId);
        return ResponseEntity.ok().body("ok");
    }

}
