package com.rawlabs.trainingspringboot.service;

import com.rawlabs.trainingspringboot.constant.StockType;
import com.rawlabs.trainingspringboot.domain.dao.Author;
import com.rawlabs.trainingspringboot.domain.dao.Book;
import com.rawlabs.trainingspringboot.domain.dao.Category;
import com.rawlabs.trainingspringboot.domain.dto.BookDto;
import com.rawlabs.trainingspringboot.domain.dto.StockDto;
import com.rawlabs.trainingspringboot.repository.AuthorRepository;
import com.rawlabs.trainingspringboot.repository.BookRepository;
import com.rawlabs.trainingspringboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookService(AuthorRepository authorRepository, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public Book save(BookDto request) {
        Optional<Author> author = authorRepository.findById(request.getAuthorId());
        if (author.isEmpty()) {
            throw new RuntimeException("Author not found");
        }

        Optional<Category> category = categoryRepository.findById(request.getCategoryId());
        if (category.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Book book = Book.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .createdDate(LocalDateTime.now())
                .isDeleted(Boolean.FALSE)
                .stock(0)
                .author(author.get())
                .category(category.get())
                .build();
        return bookRepository.save(book);
    }

    public Book update(BookDto request, Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) throw new NullPointerException("Data not found");

        Book book = bookOptional.get();
        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) return null;
        return book.get();
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByCategory(String categoryName) {
        Category category = categoryRepository.findCategoryByNameIgnoreCase(categoryName);
        if (category != null) return category.getBooks();

        return new ArrayList<>();
    }

    public List<Book> getBooksByAuthor(String authorName) {
        Author author = authorRepository.findAuthorByNameIgnoreCase(authorName);
        if (author != null) return author.getBooks();
        return new ArrayList<>();
    }

    public List<Book> getBooksByCategoryAndAuthor(String category, String author) {
        return bookRepository.findAllByCategoryAndAuthor(category, author);
    }

    public Book updateStock(Long bookId, StockDto request) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            // Book is empty
            throw new RuntimeException("Book not found");
        }

        Book book = bookOptional.get();
        Integer stock = book.getStock();

        if (StockType.ADDITIONS.equals(request.getType())) {
            stock = stock + request.getValue();
        } else {
            stock = stock - request.getValue();
        }

        book.setStock(stock);
        return bookRepository.save(book);
    }

}
