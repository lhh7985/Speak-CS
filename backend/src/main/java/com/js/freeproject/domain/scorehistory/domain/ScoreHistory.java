package com.js.freeproject.domain.scorehistory.domain;

import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.user.domain.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class ScoreHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int score;

    private LocalDateTime createDate;
}
