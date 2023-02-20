package com.rawlabs.trainingspringboot.service;

import com.rawlabs.trainingspringboot.domain.dao.Author;
import com.rawlabs.trainingspringboot.domain.dto.AuthorDto;
import com.rawlabs.trainingspringboot.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(AuthorDto request) {
        Author author = Author.builder()
                .createdDate(LocalDateTime.now())
                .isDeleted(Boolean.FALSE)
                .name(request.getName())
                .gender(request.getGender())
                .build();
        return authorRepository.save(author);
    }

}
