package com.js.freeproject.domain.comment.controller;

import com.js.freeproject.domain.comment.application.CommentService;
import com.js.freeproject.domain.comment.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseEntity<?> saveComment(@PathVariable final Long boardId, @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok().body(commentService.saveComment(commentRequest,boardId));
    }

//    @GetMapping("/{boardId}")
//    public ResponseEntity<?> getComments(@PathVariable final Long boardId){
//        Map<String, List> commentMap = commentService.getCommentOfBoard(boardId);
//        return ResponseEntity.ok().body(commentMap);
//    }
}
