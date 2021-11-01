package com.js.freeproject.domain.board.controller;

import com.js.freeproject.domain.board.application.BoardService;
import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.board.dto.BoardListResponse;
import com.js.freeproject.domain.board.dto.BoardRequest;
import com.js.freeproject.domain.board.dto.BoardResponse;
import com.js.freeproject.domain.board.dto.BoardSaveResponse;
import com.js.freeproject.domain.file.application.BoardFileService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/board")
public class BoardControlller {

    private final BoardService boardService;
    private final BoardFileService boardFileService;

    @ApiOperation(value = "게시판 글 작성")
    @PostMapping("/{userId}")
    public ResponseEntity<?> saveQuestion(final BoardRequest boardRequest, @PathVariable Long userId) throws IOException {
        Board board = boardService.saveQuestion(boardRequest,userId);
        final BoardSaveResponse boardSaveResponse = new BoardSaveResponse(board);
        return ResponseEntity.ok().body(boardSaveResponse);
    }

    @ApiOperation(value = "게시판 목록 조회")
    @GetMapping
    public ResponseEntity<?> getBoardList(){
        List<BoardListResponse> boardListResponses = boardService.findAllBoard();
        return ResponseEntity.ok().body(boardListResponses);
    }

    @ApiOperation(value = "게시판 선택 항목 조회")
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardOne(@PathVariable Long boardId){
        BoardResponse boardResponse = boardService.findById(boardId);
        return ResponseEntity.ok().body(boardResponse);
    }

    @ApiOperation(value = "게시판 글 수정")
    @PutMapping("/{boardId}")
    public ResponseEntity<?> putBoard(final BoardRequest boardRequest, @PathVariable Long boardId) throws IOException {
        boardService.updateDescription(boardRequest,boardId);
        return ResponseEntity.ok().body("ok");
    }

}
