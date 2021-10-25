package com.js.freeproject.domain.board.application;

import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.board.domain.BoardRepository;
import com.js.freeproject.domain.board.dto.BoardListResponse;
import com.js.freeproject.domain.board.dto.BoardRequest;
import com.js.freeproject.domain.board.dto.BoardResponse;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Board saveQuestion(final BoardRequest boardRequest,final Long userId){
        User searchUser = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Board boardEntity = boardRequest.toEntity();
        Board question = Board.builder()
                .user(searchUser)
                .title(boardEntity.getTitle())
                .description(boardEntity.getDescription())
                .build();
        return boardRepository.save(question);
    }

    public List<BoardListResponse> findAllBoard(){
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardListResponse::ofBoard)
                .collect(Collectors.toList());
    }

    public BoardResponse findById(final Long boardId){
        Board searchBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        BoardResponse boardResponse = new BoardResponse(searchBoard);
        return boardResponse;
    }

    @Transactional
    public BoardResponse updateDescription(final BoardRequest boardRequest, final Long boardId){
        final Board updateBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        updateBoard.updateDescription(boardRequest.toEntity());
        final Board updatedBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        BoardResponse boardResponse = new BoardResponse(updatedBoard);
        return boardResponse;
    }



}
