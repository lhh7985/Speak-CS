package com.js.freeproject.domain.category.controller;

import com.js.freeproject.domain.category.application.CategoryService;
import com.js.freeproject.domain.category.domain.Category;
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

//    @PostConstruct
//    public void init(){
//        categoryService.saveCategory(new Category("운영체제","운영체제입니다."));
//        categoryService.saveCategory(new Category("네트워크","네트워크입니다."));
//        categoryService.saveCategory(new Category("스프링","스프링입니다."));
//        categoryService.saveCategory(new Category("백엔드","백엔드입니다."));
//    }
}
