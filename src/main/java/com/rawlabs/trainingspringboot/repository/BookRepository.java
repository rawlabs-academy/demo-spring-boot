package com.rawlabs.trainingspringboot.repository;

import com.rawlabs.trainingspringboot.domain.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select b from Book b where upper(b.category.name) like upper(:category) and upper(b.author.name) like upper(:author)")
    List<Book> findAllByCategoryAndAuthor(String category, String author);

    Integer countBooksByPriceBetween(Integer a, Integer b);

    @Query(value = "select min(b.stock) from Book b")
    Integer getMinimumStock();

    @Query(value = "select max(b.stock) from book", nativeQuery = true)
    Integer getMaximumStock();

}
