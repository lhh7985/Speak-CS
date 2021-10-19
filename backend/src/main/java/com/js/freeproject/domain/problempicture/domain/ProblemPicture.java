package com.js.freeproject.domain.problempicture.domain;

import javax.persistence.*;

import com.js.freeproject.domain.problem.domain.Problem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@RequiredArgsConstructor
public class ProblemPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @OneToOne(mappedBy = "problemPicture", fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    public ProblemPicture(String image) {
        this.image = image;
    }

    public ProblemPicture(String image, Problem problem) {
        this.image = image;
        this.problem = problem;
    }
}
