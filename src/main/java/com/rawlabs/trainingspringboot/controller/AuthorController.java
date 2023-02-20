package com.rawlabs.trainingspringboot.controller;

import com.rawlabs.trainingspringboot.domain.dao.Author;
import com.rawlabs.trainingspringboot.domain.dto.AuthorDto;
import com.rawlabs.trainingspringboot.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Save new author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public Author saveAuthor(@RequestBody AuthorDto request) {
        return authorService.createAuthor(request);
    }

}
