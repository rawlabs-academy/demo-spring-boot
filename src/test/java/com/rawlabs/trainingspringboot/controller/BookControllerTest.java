package com.rawlabs.trainingspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rawlabs.trainingspringboot.domain.dao.Book;
import com.rawlabs.trainingspringboot.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Configuration
    @EnableWebMvc
    static class BookControllerTestConfig {
        @Bean
        public BookController bookController(BookService bookService) {
            return new BookController(bookService);
        }
    }
    

    @Test
    void saveBook_Test() throws Exception {
        when(bookService.save(any())).thenReturn(Book.builder()
                        .id(1L)
                        .title("any")
                        .price(12000)
                .build());
        mvc.perform(post("/book")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("any"))
                .andExpect(jsonPath("$.price").value(12000));
    }

}