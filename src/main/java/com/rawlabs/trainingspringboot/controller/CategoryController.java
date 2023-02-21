package com.rawlabs.trainingspringboot.controller;

import com.rawlabs.trainingspringboot.domain.dao.Category;
import com.rawlabs.trainingspringboot.domain.dto.CategoryDto;
import com.rawlabs.trainingspringboot.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category")
@SecurityRequirement(name = "bearer")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Save new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public Category saveCategory(@RequestBody CategoryDto request) {
        return categoryService.saveCategory(request);
    }

}
