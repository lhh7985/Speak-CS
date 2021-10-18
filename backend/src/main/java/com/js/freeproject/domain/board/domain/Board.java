package com.js.freeproject.domain.board.domain;

import com.js.freeproject.domain.boardpicture.domain.BoardPic;
import com.js.freeproject.domain.comment.domain.Comment;
import com.js.freeproject.domain.file.domain.File;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    List<BoardPic> boardPicList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    List<File> fileList = new ArrayList<>();

}
