package com.js.freeproject.domain.board;

import com.js.freeproject.domain.board.application.BoardService;
import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.board.domain.BoardRepository;
import com.js.freeproject.domain.board.dto.BoardListResponse;
import com.js.freeproject.domain.board.dto.BoardRequest;
import com.js.freeproject.domain.board.dto.BoardResponse;
import com.js.freeproject.domain.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Rollback(false)
    void saveBoard() {
        User user = User.builder()
                .email("test")
                .name("test")
                .nickName("test")
                .pass("test")
                .build();
        Board question = Board.builder()
                .user(user)
                .title("게시판 제목")
                .description("게시판 내용")
                .build();
        boardRepository.save(question);
    }

    @Test
    void findBoard(){
        List<BoardListResponse> boards = boardService.findAllBoard();
        System.out.println("게시판글 수:"+boards.size());
        System.out.println(boards.get(8).getTitle());
        System.out.println(boards.get(8).getDescription());
        System.out.println(boards.get(8).getUser().getName());
    }
    
    /*
    댓글, 이미지 완료되면 그때 테스트
     */
    @Test
    void findBoardOne(){
        BoardResponse searchBoard = boardService.findById((long)9);
        System.out.println(searchBoard.getTitle());
        System.out.println(searchBoard.getDescription());
    }

    @Test
    void updateBoardOne(){
        BoardRequest boardRequest = new BoardRequest();
        boardRequest.setTitle("게시판 업데이트");
        boardRequest.setDescription("게시판 업데이트");
        boardService.updateDescription(boardRequest,(long)9);
        List<BoardListResponse> boards = boardService.findAllBoard();
        System.out.println(boards.get(8).getTitle());
        System.out.println(boards.get(8).getDescription());
        System.out.println(boards.get(8).getUser().getName());
    }


}
