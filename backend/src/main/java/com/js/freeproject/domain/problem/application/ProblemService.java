package com.js.freeproject.domain.problem.application;

import com.js.freeproject.domain.category.domain.CategoryRepository;
import com.js.freeproject.domain.problem.domain.Problem;
import com.js.freeproject.domain.problem.domain.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;

    @Transactional
    public void saveProblem(Problem problem){
        problemRepository.save(problem);
    }

    public List<Problem> findProblems(){
        return problemRepository.findAll();
    }

//    public List<Problem> findProblemsByCategoryId(){
//        return problemRepository.findByCategory();
//    }

    @
}
