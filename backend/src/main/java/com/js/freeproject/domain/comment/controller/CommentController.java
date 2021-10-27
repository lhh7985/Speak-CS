package com.js.freeproject.domain.comment.controller;

import com.js.freeproject.domain.comment.application.CommentService;
import com.js.freeproject.domain.comment.dto.CommentRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 및 대댓글 작성 - 댓글 parentId = 0, 대댓글 parentId = commentId")
    @PostMapping("/{boardId}")
    public ResponseEntity<?> saveComment(@PathVariable final Long boardId, @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok().body(commentService.saveComment(commentRequest,boardId));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> getComments(@PathVariable final Long boardId){
        Map<String, List> commentMap = commentService.getCommentOfBoard(boardId);
        return ResponseEntity.ok().body(commentMap);
    }
}
