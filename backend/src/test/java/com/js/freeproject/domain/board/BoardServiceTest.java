package com.js.freeproject.domain.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.freeproject.domain.board.application.BoardService;
import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.board.domain.BoardRepository;
import com.js.freeproject.domain.board.dto.BoardListResponse;
import com.js.freeproject.domain.board.dto.BoardRequest;
import com.js.freeproject.domain.board.dto.BoardResponse;
import com.js.freeproject.domain.comment.dto.CommentRequest;
import com.js.freeproject.domain.user.domain.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    void updateBoardOne() throws IOException {
        BoardRequest boardRequest = new BoardRequest();
        boardRequest.setTitle("게시판 업데이트");
        boardRequest.setDescription("게시판 업데이트");
        boardService.updateDescription(boardRequest,(long)9);
        List<BoardListResponse> boards = boardService.findAllBoard();
        System.out.println(boards.get(8).getTitle());
        System.out.println(boards.get(8).getDescription());
        System.out.println(boards.get(8).getUser().getName());
    }

//####################################
    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper OM;

    @Test
    @Order(1)
    void saveBoard2() throws Exception {
        BoardRequest br = new BoardRequest();
        br.setDescription("Board Description Test");
        br.setTitle("Board Title Test");

        String comment = OM.writeValueAsString(br);

        mvc.perform(post("/api/v1/board/"+1)
                        .content(comment)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void findBoardOne2() throws Exception{
        mvc.perform(get("/api/v1/board/"+1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void findBoardList2() throws Exception {
        mvc.perform(get("/api/v1/board/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
