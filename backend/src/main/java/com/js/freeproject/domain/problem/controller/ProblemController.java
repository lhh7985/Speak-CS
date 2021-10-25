package com.js.freeproject.domain.problem.controller;

import com.js.freeproject.domain.category.application.CategoryService;
import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.model.CommonResponse;
import com.js.freeproject.domain.problem.application.ProblemService;
import com.js.freeproject.domain.problem.domain.AnswerDto;
import com.js.freeproject.domain.problem.domain.ProblemDto;
import com.js.freeproject.domain.problem.domain.ProblemStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity makeProblem(@RequestBody ProblemMakeRequest problemMakeRequest){
        Long saveId = problemService.saveProblem(problemMakeRequest);
        return ResponseEntity.status(HttpStatus.OK).body("저장이 완료되었습니다 문제 Id : "+ saveId);
    }

    @GetMapping("/{id}")
    public ResponseEntity categoryProblems(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        final CommonResponse result = new CommonResponse();
        if (!category.isPresent()) {
            result.message = "관련 카테고리가 없습니다";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(result);
        }

        List<ProblemDto> problems = problemService.findByCategory(category.get());
        if(problems.isEmpty()){
            result.message = "관련 카테고리에 존재하는 문제가 없습니다";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result);
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
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.message = problemService.updateStatus(evaluateRequest.getId(), evaluateRequest.getProblemStatus());

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
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
