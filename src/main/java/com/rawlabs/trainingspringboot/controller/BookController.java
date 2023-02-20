package com.rawlabs.trainingspringboot.controller;

import com.rawlabs.trainingspringboot.domain.dao.Book;
import com.rawlabs.trainingspringboot.domain.dto.BookDto;
import com.rawlabs.trainingspringboot.domain.dto.StockDto;
import com.rawlabs.trainingspringboot.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
@SecurityRequirement(name = "bearer")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Save new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public Book saveBook(@RequestBody BookDto request) {
        return bookService.save(request);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public List<Book> getAllBooks(@RequestParam(value = "category", required = false) String category,
                                  @RequestParam(value = "author", required = false) String author) {
        if (StringUtils.isNotEmpty(category) && StringUtils.isNotEmpty(author)) {
            return bookService.getBooksByCategoryAndAuthor(category, author);
        }

        if (StringUtils.isNotEmpty(category)) {
            return bookService.getBooksByCategory(category);
        }

        if (StringUtils.isNotEmpty(author)) {
            return bookService.getBooksByAuthor(author);
        }

        return bookService.getBooks();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public Book getById(@PathVariable(value = "id") Long id) {
        return bookService.getById(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public Book updateBook(@PathVariable(value = "id") Long id, @RequestBody BookDto request) {
        return bookService.update(request, id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public void deleteBook(@PathVariable(value = "id") Long id) {
        bookService.deleteById(id);
    }

    @PatchMapping(value = "/{id}/update-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update book stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public Book updateStock(@PathVariable(value = "id") Long id, @RequestBody StockDto request) {
        return bookService.updateStock(id, request);
    }

}
