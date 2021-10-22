package com.js.freeproject.domain.problem.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProblemDto {
    private Long id;
    private String description;
    private List<String> problemPicture;
    private String user;

    public ProblemDto(Problem problem) {
        this.problemPicture = new ArrayList<>();
        this.id = problem.getId();
        this.description = problem.getDescription();
        problem.getProblemPicture().stream().forEach(o -> this.problemPicture.add(o.getImage()));
        this.user = problem.getUser().getName();
    }
}
