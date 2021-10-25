package com.js.freeproject.domain.problem.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.js.freeproject.domain.answer.domain.Answer;
import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.problempicture.domain.ProblemPicture;

import com.js.freeproject.domain.user.domain.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Slf4j
@RequiredArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProblemStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<ProblemPicture> problemPicture = new ArrayList<>();

    public void setCategory(Category category) {
        this.category = category;
        category.getProblems().add(this);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProblemPictures(ProblemPicture problemPicture) {
        problemPicture = new ProblemPicture(problemPicture.getImage(), this);
        this.problemPicture.add(problemPicture);
    }

    public void addProblemAnswer(Answer answer) {
        answer = new Answer(answer.getWord(), this);
        this.answers.add(answer);
    }

    public void setStatus(ProblemStatus status){
        this.status = status;
    }

    public static Problem createProblem(String description, Category category, User user, List<ProblemPicture> problemPictures, List<Answer> answers) {
        Problem problem = new Problem(description, ProblemStatus.wait);
        problem.setCategory(category);
        problem.setUser(user);
        for (ProblemPicture problemPicture: problemPictures) {
            problem.setProblemPictures(problemPicture);
        }
        for (Answer answer : answers) {
            problem.addProblemAnswer(answer);
        }
        return problem;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", category=" + category +
                ", answers=" + answers +
                ", problemPicture=" + problemPicture +
                '}';
    }

    public Problem(String description, ProblemStatus status) {
        this.description = description;
        this.status = status;
    }
}
