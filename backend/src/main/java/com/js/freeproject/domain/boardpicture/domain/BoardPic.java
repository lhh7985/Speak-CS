package com.js.freeproject.domain.boardpicture.domain;

import com.js.freeproject.domain.board.domain.Board;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BoardPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="board_id")
    private Board board;
}
