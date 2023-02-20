package com.rawlabs.trainingspringboot.repository;

import com.rawlabs.trainingspringboot.domain.dao.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findAuthorByNameIgnoreCase(String name);

}
