package com.js.freeproject.domain.file.domain;

import com.js.freeproject.domain.board.domain.Board;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="board_id")
    private Board board;
}
