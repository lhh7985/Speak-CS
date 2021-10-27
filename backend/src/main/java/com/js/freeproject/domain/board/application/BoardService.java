package com.js.freeproject.domain.board.application;

//import com.js.freeproject.domain.amazonS3.S3Service;
import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.board.domain.BoardRepository;
import com.js.freeproject.domain.board.dto.BoardListResponse;
import com.js.freeproject.domain.board.dto.BoardRequest;
import com.js.freeproject.domain.board.dto.BoardResponse;
import com.js.freeproject.domain.comment.application.CommentService;
import com.js.freeproject.domain.file.application.BoardFileService;
import com.js.freeproject.domain.file.domain.BoardFile;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private final BoardFileService boardFileService;
    private final CommentService commentService;

    @Transactional
    public Board saveQuestion(final BoardRequest boardRequest,final Long userId) throws IOException {
        User searchUser = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Board boardEntity = boardRequest.toEntity();
        Board question = Board.builder()
                .user(searchUser)
                .title(boardEntity.getTitle())
                .description(boardEntity.getDescription())
                .build();
        Board savedBoard = boardRepository.save(question);

        if(boardRequest.getBoardFiles()!=null){
            boardFileService.saveBoardFiles(boardRequest.getBoardFiles(), savedBoard.getId());
        }
        return savedBoard;
    }

    public List<BoardListResponse> findAllBoard(){
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardListResponse::ofBoard)
                .collect(Collectors.toList());
    }

    public BoardResponse findById(final Long boardId){
        Board searchBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        List<BoardFile> searchBoardFiles = boardFileService.findBoardFiles(boardId);
        //댓글들을 담기
        Map<String, List> commentOfBoard = commentService.getCommentOfBoard(searchBoard.getComments());
        //파일 있으면 가져오기
        commentOfBoard.put("files", searchBoardFiles);
        BoardResponse boardResponse = new BoardResponse(searchBoard,commentOfBoard);
        return boardResponse;
    }

    @Transactional
    public void updateDescription(final BoardRequest boardRequest, final Long boardId) throws IOException {
        final Board updateBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        updateBoard.updateDescription(boardRequest.toEntity());
        boardFileService.updateBoardFile(boardRequest.getBoardFiles(), boardId);
    }



}
