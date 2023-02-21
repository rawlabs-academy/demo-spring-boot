package com.rawlabs.trainingspringboot.service;

import com.rawlabs.trainingspringboot.constant.Gender;
import com.rawlabs.trainingspringboot.domain.dao.Author;
import com.rawlabs.trainingspringboot.domain.dao.Book;
import com.rawlabs.trainingspringboot.domain.dao.Category;
import com.rawlabs.trainingspringboot.domain.dto.BookDto;
import com.rawlabs.trainingspringboot.repository.AuthorRepository;
import com.rawlabs.trainingspringboot.repository.BookRepository;
import com.rawlabs.trainingspringboot.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = BookService.class)
class BookServiceTest {

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private BookService bookService;

    @Test
    void save_SuccessTest() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(Author.builder()
                        .id(1L)
                        .gender(Gender.M)
                        .name("Any")
                        .isDeleted(Boolean.FALSE)
                .build()));
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(Category.builder()
                        .id(1L)
                        .name("any")
                .build()));
        when(bookRepository.save(any())).thenReturn(Book.builder()
                        .id(1L)
                        .stock(0)
                        .author(Author.builder().id(1L).build())
                        .category(Category.builder().id(1L).build())
                        .title("Any")
                        .price(120000)
                        .createdDate(LocalDateTime.now())
                .build());

        Book book = bookService.save(BookDto.builder()
                        .title("Any")
                        .authorId(1L)
                        .categoryId(1L)
                        .price(150000)
                .build());
        assertNotNull(book);
        assertEquals(1L, book.getId());
        assertEquals(0, book.getStock());
        assertEquals("Any", book.getTitle());
        assertEquals(1L, book.getAuthor().getId());
        assertEquals(1L, book.getCategory().getId());
        assertEquals(120000, book.getPrice());
    }

    @Test
    void save_CategoryNotFoundTest() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(Author.builder()
                .id(1L)
                .gender(Gender.M)
                .name("Any")
                .isDeleted(Boolean.FALSE)
                .build()));
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> bookService.save(BookDto.builder()
                .title("Any")
                .authorId(1L)
                .categoryId(1L)
                .price(150000)
                .build()));
    }

    @Test
    void save_AuthorNotFoundTest() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> bookService.save(BookDto.builder()
                .title("Any")
                .authorId(1L)
                .categoryId(1L)
                .price(150000)
                .build()));
    }

}