package com.js.freeproject.domain.problem.domain;

import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.problempicture.domain.ProblemPic;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Problem {

    @Id @GeneratedValue
    private Long id;

    private String description;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "problempic_id")
    private ProblemPic problemPic;

}
