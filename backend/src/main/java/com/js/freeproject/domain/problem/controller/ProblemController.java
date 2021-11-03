package com.js.freeproject.domain.problem.controller;

import com.js.freeproject.domain.category.application.CategoryService;
import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.model.CommonResponse;
import com.js.freeproject.domain.problem.application.ProblemService;
import com.js.freeproject.domain.problem.domain.AnswerResponse;
import com.js.freeproject.domain.problem.domain.ProblemMakeRequest;
import com.js.freeproject.domain.problem.domain.ProblemResponse;
import com.js.freeproject.domain.problem.domain.ProblemStatus;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Slf4j
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {

    private final CategoryService categoryService;
    private final ProblemService problemService;

    @PostMapping("/make")
    @ApiOperation(value = "문제만들기")
    public ResponseEntity<String> makeProblem(@Valid @RequestBody ProblemMakeRequest problemMakeRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        Long saveId = problemService.saveProblem(problemMakeRequest);
        return ResponseEntity.status(HttpStatus.OK).body("저장이 완료되었습니다 문제 Id : "+ saveId);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "카테고리별로 문제 가지고 오기(현재 3개이하)")
    public ResponseEntity categoryProblems(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("관련 카테고리가 없습니다."));
        List<ProblemResponse> problems = problemService.findByCategory(category);
        if(problems.isEmpty()){
            throw new IllegalStateException("관련 카테고리에 존재하는 문제가 없습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(problems);
    }

    @GetMapping("category/{id}")
    @ApiOperation(value = "해당 카테고리 문제 개수 가져오기")
    public ResponseEntity categoryProblemNum(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("관련 카테고리가 없습니다."));
        long problems = problemService.findByCategorySize(category);
        return ResponseEntity.status(HttpStatus.OK).body(problems);
    }

    @GetMapping("/wait")
    @ApiOperation(value = "승인대기상태 문제가지고오기")
    public ResponseEntity waitProblems(){
        List<ProblemResponse> waitProblems = problemService.findByStatus(ProblemStatus.wait);
        if(waitProblems.isEmpty()){
            throw new IllegalStateException("승인 대기중인 문제가 없습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(waitProblems);
    }

    @PostMapping("/evaluate")
    @ApiOperation(value = "관리자가 문제상태 (대기, 승인, 거부) 변경하기")
    public ResponseEntity evaluateProblems(@Valid @RequestBody EvaluateRequest evaluateRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.message = problemService.updateStatus(evaluateRequest.getId(), evaluateRequest.getProblemStatus());

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @PostMapping("/checkanswer")
    @ApiOperation(value = "문제 정답 맞추기")
    public ResponseEntity checkAnswer(@Valid @RequestBody CheckAnswerRequest checkAnswerRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }
        AnswerResponse answerResponse = problemService
                .findAnswer(checkAnswerRequest.getId(), checkAnswerRequest.getMyAnswer());
        return ResponseEntity.status(HttpStatus.OK).body(answerResponse);
    }


    @Getter
    static class EvaluateRequest{
        @NotNull(message = "문제 id를 입력해주세요.")
        private Long id;
        private ProblemStatus problemStatus;
    }

    @Getter
    static class CheckAnswerRequest{
        @NotNull(message = "문제 id를 입력해주세요.")
        private Long id;
        private String myAnswer;
    }
}
