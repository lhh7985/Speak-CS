package com.js.freeproject.domain.problem.domain;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
class SaveRequest{
    private String userId;
    private String categoryId;
    private String description;
    private List<String> answer;
    private List<MultipartFile> picture;
}