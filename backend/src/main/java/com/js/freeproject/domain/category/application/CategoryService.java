package com.js.freeproject.domain.category.application;

import com.js.freeproject.domain.category.domain.Category;
import com.js.freeproject.domain.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    @Transactional
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> findCategorys(){
        return categoryRepository.findAll();
    }
}
