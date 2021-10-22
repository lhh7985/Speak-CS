package com.js.freeproject.domain.problem.controller;

import com.js.freeproject.domain.category.application.CategoryService;
import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.problem.application.ProblemService;
import com.js.freeproject.domain.problem.domain.AnswerDto;
import com.js.freeproject.domain.problem.domain.ProblemDto;
import com.js.freeproject.domain.problem.domain.ProblemStatus;
import com.js.freeproject.domain.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Slf4j
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {

    private final CategoryService categoryService;
    private final ProblemService problemService;

    @PostMapping("/make")
    public ResponseEntity makeProblem(@RequestBody SavedRequest savedRequest){
        problemService.saveProblem(savedRequest);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/{id}")
    public ResponseEntity categoryProblems(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);

        if (!category.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("관련 카테고리가 없습니다");
        }

        List<ProblemDto> problems = problemService.findByCategory(category.get());
        if(problems.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 카테고리에 문제가 없습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(problems);
    }

    @GetMapping("/wait")
    public ResponseEntity waitProblems(){
        List<ProblemDto> waitProblems = problemService.findByStatus(ProblemStatus.wait);
        if(waitProblems.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("승인 대기중인 문제가 없습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(waitProblems);
    }

    @PostMapping("/evaluate")
    public ResponseEntity evaluateProblems(@RequestBody EvaluateRequest evaluateRequest){

        String message = problemService.updateStatus(evaluateRequest.getId(), evaluateRequest.getProblemStatus());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PostMapping("/checkanswer")
    public ResponseEntity checkAnswer(@RequestBody CheckAnswerRequest checkAnswerRequest){
        AnswerDto answerDto = problemService
                .findAnswer(checkAnswerRequest.getId(), checkAnswerRequest.getMyAnswer());
        return ResponseEntity.status(HttpStatus.OK).body(answerDto);
    }


    @Getter
    static class EvaluateRequest{
        private Long id;
        private ProblemStatus problemStatus;
    }

    @Getter
    static class CheckAnswerRequest{
        private Long id;
        private String myAnswer;
    }


}
