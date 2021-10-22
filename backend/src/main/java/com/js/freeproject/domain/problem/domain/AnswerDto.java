package com.js.freeproject.domain.problem.domain;

import com.js.freeproject.domain.answer.domain.Answer;
import lombok.Getter;

import java.util.List;

@Getter
public class AnswerDto {
    private List<String> proper;
    private List<String> wrong;

    public AnswerDto(List<String> proper, List<String> wrong) {
        this.proper = proper;
        this.wrong = wrong;
    }
}
