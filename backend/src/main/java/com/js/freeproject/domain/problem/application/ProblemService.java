package com.js.freeproject.domain.problem.application;

import com.js.freeproject.domain.answer.domain.Answer;
import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.category.domain.CategoryRepository;
import com.js.freeproject.domain.problem.domain.ProblemMakeRequest;
import com.js.freeproject.domain.problem.domain.*;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long saveProblem(ProblemMakeRequest problemMakeRequest) {
        User user = userRepository.findById(problemMakeRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        Category category = categoryRepository.findById(problemMakeRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        List<Answer> answers =
                problemMakeRequest.getAnswers().stream().map(Answer::new).collect(toList());
        Problem newProblem = Problem.createProblem(
                problemMakeRequest.getDescription(),
                category,
                user,
                new ArrayList<>(),
                answers);
        try {
            problemRepository.save(newProblem);
        }catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
        return newProblem.getId();
    }

    public List<Problem> findProblems() {
        return problemRepository.findAll();
    }

    public Optional<Problem> findProblem(Long id) {
        return problemRepository.findById(id);
    }

    public List<ProblemResponse> findByCategory(Category category) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Problem> problems = problemRepository.findByCategory(category.getName(), pageRequest);
        Collections.shuffle(problems);
        int size = problems.size();
        return problems.subList(0, size<3? size:3).stream().map(ProblemResponse::new).collect(toList());
    }

    public Long findByCategorySize(Category category) {
        PageRequest pageRequest = PageRequest.of(0, 1000);
        List<Problem> problems = problemRepository.findByCategory(category.getName(), pageRequest);
        long size = problems.size();
        return size;
    }

    public List<ProblemResponse> findByStatus(ProblemStatus problemStatus) {
        List<Problem> problems = problemRepository.findByStatus(problemStatus);
        List<ProblemResponse> problemResponses = problems.stream()
                .map(ProblemResponse::new).collect(toList());
        return problemResponses;
    }

    public AnswerResponse findAnswer(Long id, String myAns) {
        Problem problem = problemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("문제에 답을 찾을 수 없습니다."));
        List<String> proper = new ArrayList<>();
        List<String> wrong = new ArrayList<>();
        extractedAnswer(myAns, problem, proper, wrong);
        AnswerResponse answerResponse = new AnswerResponse(proper, wrong);
        return answerResponse;
    }

    private void extractedAnswer(String myAns, Problem problem, List<String> proper, List<String> wrong) {
        for (Answer answer : problem.getAnswers()) {
            if (myAns.contains(answer.getWord())) {
                proper.add(answer.getWord());
            } else {
                wrong.add(answer.getWord());
            }
        }
    }

    @Transactional
    public String updateStatus(Long id, ProblemStatus problemStatus) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("문제가 존재하지 않습니다."));

        problem.setStatus(problemStatus);
        return "변경이 완료되었습니다.";
    }
}
