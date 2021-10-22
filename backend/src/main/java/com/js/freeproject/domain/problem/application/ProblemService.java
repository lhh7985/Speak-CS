package com.js.freeproject.domain.problem.application;

import com.js.freeproject.domain.answer.domain.Answer;
import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.problem.domain.*;
import com.js.freeproject.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;

    @Transactional
    public void saveProblem(SavedRequest savedRequest){
//        if(!user.getRole().equals("admin")){
//            throw new IllegalStateException("관리자가 아닙니다.");
//        }
        return ;
        //problemRepository.save();
    }

    public List<Problem> findProblems(){
        return problemRepository.findAll();
    }
    public Optional<Problem> findProblem(Long id){
        return problemRepository.findById(id);
    }

    public List<ProblemDto> findByCategory(Category category){
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<Problem> problems = problemRepository.findByCategory(category.getName(), pageRequest);
        return problems.stream().map(ProblemDto::new).collect(toList());
    }

    public List<ProblemDto> findByStatus(ProblemStatus problemStatus){
        List<Problem> problems = problemRepository.findByStatus(problemStatus);
        List<ProblemDto> problemDtos = problems.stream()
                .map(ProblemDto::new).collect(toList());
        return problemDtos;
    }

    public AnswerDto findAnswer(Long id,String myAns){
        Optional<Problem> problems = problemRepository.findById(id);
        problems.orElseThrow(() -> new IllegalStateException("문제에 답을 찾을 수 없습니다."));
        List<String> proper = new ArrayList<>();
        List<String> wrong = new ArrayList<>();
        for(Answer answer : problems.get().getAnswers()){
            if(myAns.contains(answer.getWord())){
                proper.add(answer.getWord());
            }else{
                wrong.add(answer.getWord());
            }
        }
        AnswerDto answerDto = new AnswerDto(proper,wrong);
        return answerDto;
    }

    @Transactional
    public String updateStatus(Long id, ProblemStatus problemStatus){
        Optional<Problem> problem = problemRepository.findById(id);
        if(!problem.isPresent()){
            return "문제가 존재하지 않습니다.";
        }
        problem.get().setStatus(problemStatus);
        return "변경이 완료되었습니다.";
    }
}
