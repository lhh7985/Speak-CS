package com.js.freeproject.domain.problem.controller;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class ProblemMakeRequest {
    private Long userId;
    private Long categoryId;
    private String description;
    private List<String> answers;
    private List<MultipartFile> picture;
}