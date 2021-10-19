package com.js.freeproject.domain.problem;

import com.js.freeproject.domain.answer.domain.Answer;
import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.category.domain.CategoryRepository;
import com.js.freeproject.domain.problem.application.ProblemService;
import com.js.freeproject.domain.problem.domain.Problem;
import com.js.freeproject.domain.problem.domain.ProblemRepository;
import com.js.freeproject.domain.problempicture.domain.ProblemPicture;
import com.js.freeproject.domain.problempicture.domain.ProblemPictureRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProblemServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    ProblemService problemService;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    ProblemPictureRepository problemPictureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    @Rollback(false)
    public void before() {
        Category category = new Category("운영체제");
        em.persist(category);

        Category category2 = new Category("네트워크");
        em.persist(category2);

        em.flush();
        em.clear();
    }

    @Test
    @Rollback(false)
    void saveProblem() {
        Category category = new Category("스프링");
        em.persist(category);

        ProblemPicture problemPicture = new ProblemPicture("디폴트이미지 url");
        Answer answer1 = Answer.createAnswer("mvc");
        Answer answer2 = Answer.createAnswer("모델");
        Answer answer3 = Answer.createAnswer("스프링부트");
        Answer answer4 = Answer.createAnswer("클라우드");
        Answer answer5 = Answer.createAnswer("배치");

        Problem problem = Problem.createProblem("스프링이 무엇인가?",
                category, problemPicture, answer1, answer2,answer3,answer4,answer5);
        System.out.println(problem);
        em.persist(problem);
        em.flush();
        em.clear();


        List<Problem> all = problemRepository.findAll();
        for (Problem p: all) {
            System.out.println(p);
        }
        //assertThat(size).isEqualTo(1);
    }

    @Test
    void findProblems() {
        List<Problem> problems = problemService.findProblems();
        for (Problem p : problems) {
            System.out.println("문제는: " + p.toString());
        }
    }

    @Test
    void findProblemsByCategoryId() {
        Category category = new Category("스프링");
        em.persist(category);

        ProblemPicture problemPicture = new ProblemPicture("디폴트이미지 url");
        Answer answer1 = Answer.createAnswer("mvc");
        Answer answer2 = Answer.createAnswer("모델");
        Answer answer3 = Answer.createAnswer("스프링부트");
        Answer answer4 = Answer.createAnswer("클라우드");
        Answer answer5 = Answer.createAnswer("배치");

        Problem problem = Problem.createProblem("스프링이 무엇인가?",
                category, problemPicture, answer1, answer2,answer3,answer4,answer5);
        //System.out.println(problem);
        em.persist(problem);


        Problem problem2 = Problem.createProblem("REST API란?",
                category, problemPicture, answer3,answer4,answer5);
        em.persist(problem2);

        em.flush();
        em.clear();

        List<Problem> all = problemRepository.findByCategory("스프링");
        for (Problem p: all) {
            System.out.println(p.getDescription());
        }
        assertThat(all.size()).isEqualTo(2);

    }
}