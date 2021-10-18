package com.js.freeproject.domain.comment.domain;

import com.js.freeproject.domain.board.domain.Board;
import lombok.Getter;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

}
