package com.js.freeproject.domain.category.application;

import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> findCategorys(){
        return categoryRepository.findAll();
    }
}
