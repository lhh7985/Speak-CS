package com.js.freeproject.domain.problem.domain;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class ProblemMakeRequest {
    @NotNull(message= "userId가 입력되지 않았습니다.")
    private Long userId;

    @NotNull(message= "categoryId가 입력되지 않았습니다.")
    private Long categoryId;

    @NotBlank(message = "문제를 만들어주세요.")
    @Size(min = 5, max = 400,message = "문제길이는 5글자이상 400글자 이하입니다.")
    private String description;

    @NotEmpty(message = "문제정답을 알려주세요.")
    private List<String> answers;
    private List<MultipartFile> picture;
}