package com.js.freeproject.domain.problempicture.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.js.freeproject.domain.problem.domain.Problem;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProblemPic {

    @Id
    @GeneratedValue
    private Long id;

    private String image;

    @JsonIgnore
    @OneToOne(mappedBy = "problemPic", fetch = FetchType.LAZY)
    private Problem problem;
}
