package com.js.freeproject.domain.problem;

import com.js.freeproject.domain.answer.domain.Answer;
import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.category.domain.CategoryRepository;
import com.js.freeproject.domain.problem.application.ProblemService;
import com.js.freeproject.domain.problem.domain.Problem;
import com.js.freeproject.domain.problem.domain.ProblemRepository;
import com.js.freeproject.domain.problem.domain.ProblemStatus;
import com.js.freeproject.domain.problempicture.domain.ProblemPicture;
import com.js.freeproject.domain.problempicture.domain.ProblemPictureRepository;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
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

    @Autowired
    UserRepository userRepository;

//    @BeforeEach
//    @Rollback(false)
//    public void before() {
//        Category category = new Category("운영체제", "운영체제입니다.");
//        em.persist(category);
//
//        Category category2 = new Category("네트워크", "네트워크입니다.");
//        em.persist(category2);
//
//        em.flush();
//        em.clear();
//    }

    @Test
    @Rollback(false)
    void saveProblem() {
        //Category category = new Category("스프링", "스프링이 무엇일까");
        //em.persist(category);
        Category category = categoryRepository.findById(3L).get();
        //User user = new User("sgs1159@naver.com", "순조에용", "이조순", "1234", "팀원", "유저디폴트이미지");
        User user = userRepository.findByNickName("조순몬")
                .orElseThrow(() -> new IllegalArgumentException("못찾음"));

//        em.persist(user);
        ProblemPicture problemPicture = new ProblemPicture("디폴트이미지 url");

//        Answer answer1 = Answer.createAnswer("객체");
        Answer answer2 = Answer.createAnswer("데이터베이스");
        Answer answer3 = Answer.createAnswer("교환");
        List<Answer> list = new ArrayList<>();
        //list.add(answer1);
        list.add(answer2);
        list.add(answer3);

        Problem problem = Problem.createProblem("DTO를 설명해주세요.",
                category, user, new ArrayList<>(), list);
        System.out.println(problem);
        em.persist(problem);
        em.flush();
        em.clear();


        List<Problem> all = problemRepository.findAll();
        for (Problem p : all) {
            System.out.println(p);
        }
    }

    @Test
    void findProblems() {
        Optional<Problem> problems = problemRepository.findById(1L);
        for (Answer a: problems.get().getAnswers()) {
            System.out.println(a.getWord());

        }
    }
    @Test
    void findAnswer(){
        Problem problem = problemRepository.findById(1L).get();
        for (Answer answer:problem.getAnswers()) {
            System.out.println(answer.getWord());
        }
    }

//    @Test
//    @Rollback(false)
//    void findProblemsByCategoryId() {
//        //Category category = new Category("스프링", "스프링이 무엇일까");
//        //em.persist(category);
//        Category category = categoryRepository.findById(3L).get();
//        User user = new User("sgs1159@naver.com", "순조에용", "이조순", "1234", "팀원", "유저디폴트이미지");
//        em.persist(user);
//
//        ProblemPicture problemPicture = new ProblemPicture("디폴트이미지 url");
//        Answer answer1 = Answer.createAnswer("mvc");
//        Answer answer2 = Answer.createAnswer("모델");
//        Answer answer3 = Answer.createAnswer("스프링부트");
//        Answer answer4 = Answer.createAnswer("클라우드");
//        Answer answer5 = Answer.createAnswer("배치");
//
//        Problem problem = Problem.createProblem("스프링이 무엇인가?",
//                category, user, problemPicture, answer1, answer2, answer3, answer4, answer5);
//        //System.out.println(problem);
//        em.persist(problem);
//
//
//        Problem problem2 = Problem.createProblem("REST API란?",
//                category, user, problemPicture, answer3, answer4, answer5);
//        em.persist(problem2);
//
//        Problem problem3 = Problem.createProblem("이게스프링이지",
//                category, user, problemPicture, answer2);
//        em.persist(problem3);
//        PageRequest pageRequest = PageRequest.of(0, 3);
//
//
//        em.flush();
//        em.clear();
//
//        List<Problem> all = problemRepository.findByCategory("스프링", pageRequest);
//        for (Problem p : all) {
//            System.out.println(p);
//        }
//        assertThat(all.size()).isEqualTo(3);
//
//    }
//
//    @Test
//    @Rollback(false)
//    void findByStatus() {
//        Category category = new Category("스프링", "스프링이 무엇일까");
//        em.persist(category);
//
//        User user = new User("sgs1159@naver.com", "순조에용", "이조순", "1234", "팀원", "유저디폴트이미지");
//        em.persist(user);
//
//        ProblemPicture problemPicture = new ProblemPicture("디폴트이미지 url");
//        Answer answer1 = Answer.createAnswer("mvc");
//        Answer answer2 = Answer.createAnswer("모델");
//        Answer answer3 = Answer.createAnswer("스프링부트");
//        Answer answer4 = Answer.createAnswer("클라우드");
//        Answer answer5 = Answer.createAnswer("배치");
//
//        Problem problem = Problem.createProblem("스프링이 무엇인가?",
//                category, user, problemPicture, answer1, answer2, answer3, answer4, answer5);
//        em.persist(problem);
//
//        Problem problem2 = Problem.createProblem("REST API란?",
//                category, user, problemPicture, answer3, answer4, answer5);
//        em.persist(problem2);
//
//        em.flush();
//        em.clear();
//
//        List<Problem> waitList = problemRepository.findByStatus(ProblemStatus.wait);
//        for (Problem p: waitList) {
//            System.out.println(p);
//        }
//    }
//
    @Test
    @Rollback(false)
    void updateProblem(){
        List<Problem> one = problemRepository.findAll();
        for(Problem p: one){
            p.setStatus(ProblemStatus.accept);
        }
        em.flush();
        em.clear();

    }

}