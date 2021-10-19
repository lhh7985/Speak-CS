package com.js.freeproject.domain.answer.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.js.freeproject.domain.problem.domain.Problem;

import lombok.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    public static Answer createAnswer(String word){
        Answer answer = new Answer(word);
        return answer;
    }

    public Answer(String word) {
        this.word = word;
    }

    public Answer(String word, Problem problem) {
        this.word = word;
        this.problem = problem;
    }
}
