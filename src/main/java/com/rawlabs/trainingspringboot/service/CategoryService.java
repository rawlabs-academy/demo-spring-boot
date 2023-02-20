package com.rawlabs.trainingspringboot.service;

import com.rawlabs.trainingspringboot.domain.dao.Category;
import com.rawlabs.trainingspringboot.domain.dto.CategoryDto;
import com.rawlabs.trainingspringboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(CategoryDto request) {
        Category category = Category.builder()
                .createdDate(LocalDateTime.now())
                .isDeleted(Boolean.FALSE)
                .name(request.getName())
                .build();

        return categoryRepository.save(category);
    }

}
