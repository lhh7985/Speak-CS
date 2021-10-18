package com.js.freeproject.domain.answer.domain;

import com.js.freeproject.domain.problem.domain.Problem;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="problem_id")
    private List<Problem> problem;
}
