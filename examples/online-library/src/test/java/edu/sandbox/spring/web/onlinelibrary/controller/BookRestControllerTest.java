package edu.sandbox.spring.web.onlinelibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sandbox.spring.web.onlinelibrary.dto.AuthorDto;
import edu.sandbox.spring.web.onlinelibrary.dto.BookDto;
import edu.sandbox.spring.web.onlinelibrary.dto.CommentDto;
import edu.sandbox.spring.web.onlinelibrary.dto.GenreDto;
import edu.sandbox.spring.web.onlinelibrary.services.BooksService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookRestController.class)
class BookRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BooksService booksService;

    @Test
    @DisplayName("should return all books")
    void shouldReturnAllBooks() throws Exception {
        var books = List.of(
                new BookDto(1L, "Book 1", new GenreDto(1L, "Genre 1"), List.of(new AuthorDto(1L, "Author 1")), null),
                new BookDto(2L, "Book 2", new GenreDto(2L, "Genre 2"), List.of(new AuthorDto(2L, "Author 2")), null)
        );
        when(booksService.findAll()).thenReturn(books);
        mvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(books)));
    }

    @Test
    @DisplayName("should return a book by id")
    void shouldReturnBookById() throws Exception {
        var book = new BookDto(
                1L,
                "book",
                new GenreDto(1L, "genre"),
                List.of(new AuthorDto(1L, "author")),
                List.of(new CommentDto(1L, 1L, "text"))
        );
        when(booksService.findById(1L)).thenReturn(Optional.of(book));
        mvc.perform(get("/api/v1/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(book)));
    }

    @Test
    @DisplayName("should create a new book")
    void shouldCreateNewBook() throws Exception {
        var book = new BookDto(
                1L,
                "book",
                new GenreDto(1L, "genre"),
                List.of(new AuthorDto(1L, "author")),
                List.of(new CommentDto(1L, 1L, "text"))
        );
        mvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(book)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("should update a book")
    void shouldUpdateBook() throws Exception {
        var book = new BookDto(
                1L,
                "book",
                new GenreDto(1L, "genre"),
                List.of(new AuthorDto(1L, "author")),
                List.of(new CommentDto(1L, 1L, "text"))
        );
        mvc.perform(put("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should delete a book by id")
    void shouldDeleteBookById() throws Exception {
        mvc.perform(delete("/api/v1/books/1")).andExpect(status().isNoContent());
    }
}