package com.js.freeproject.domain.category.controller;

import com.js.freeproject.domain.category.application.CategoryService;
import com.js.freeproject.domain.category.domain.Category;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    @ApiOperation(value = "모든 카테고리정보 가져오기")
    public List<CategoryDto> allCategory(){
        List<Category> categorys = categoryService.findCategorys();
        List<CategoryDto> categoryDtos = categorys.stream()
                .map(CategoryDto::new).collect(toList());
        return categoryDtos;
    }

    @Getter
    static class CategoryDto{
        private Long id;
        private String name;
        private String description;

        public CategoryDto(Category category) {
            this.id = category.getId();
            this.name = category.getName();
            this.description = category.getDescription();
        }
    }
}
