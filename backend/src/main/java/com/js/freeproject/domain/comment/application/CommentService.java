package com.js.freeproject.domain.comment.application;


import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.board.domain.BoardRepository;
import com.js.freeproject.domain.comment.domain.Comment;
import com.js.freeproject.domain.comment.domain.CommentRepository;
import com.js.freeproject.domain.comment.dto.CommentRequest;
import com.js.freeproject.domain.comment.dto.CommentResponse;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse saveComment(final CommentRequest commentRequest, final Long boardId){
        Board searchBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        User searchUser = userRepository.findById(commentRequest.getUserId()).orElseThrow(IllegalArgumentException::new);

        Comment insertComment = Comment.builder()
                .content(commentRequest.getContent())
                .user(searchUser)
                .board(searchBoard)
                .build();
        //parent != 0 대답글
        if(commentRequest.getParentId() != 0){
            Comment searchComment = commentRepository.findById(commentRequest.getParentId()).orElseThrow(IllegalArgumentException::new);
            insertComment.updateParent(searchComment);
        }
        Comment ct = commentRepository.save(insertComment);
        System.out.println(ct.getId()+" "+ct.getUser().getName()+" "+ct.getCreatedAt());
        return CommentResponse.ofComment(ct);
    }

    public Map<String,List> getCommentOfBoard(final Long boardId){
        List<Comment> allComments = commentRepository.findAllByBoardId(boardId);
        List<CommentResponse> answerComment = new ArrayList<>();
        List<CommentResponse> coComment = new ArrayList<>();
        for(Comment comment : allComments){
            if(comment.getParent() != null){
                coComment.add(CommentResponse.ofComment(comment));
            }else{
                answerComment.add(CommentResponse.ofComment(comment));
            }
        }
        Map<String, List> commentMap = new HashMap<>();
        commentMap.put("answerComment", answerComment);
        commentMap.put("coComment",coComment);
        return commentMap;
    }


}
