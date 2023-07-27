package edu.sandbox.spring.mvc.onlinelibrary.services.impl;

import edu.sandbox.spring.mvc.onlinelibrary.converters.Converter;
import edu.sandbox.spring.mvc.onlinelibrary.domain.Book;
import edu.sandbox.spring.mvc.onlinelibrary.dto.AuthorDto;
import edu.sandbox.spring.mvc.onlinelibrary.dto.BookDto;
import edu.sandbox.spring.mvc.onlinelibrary.dto.CommentDto;
import edu.sandbox.spring.mvc.onlinelibrary.dto.GenreDto;
import edu.sandbox.spring.mvc.onlinelibrary.repository.BookRepository;
import edu.sandbox.spring.mvc.onlinelibrary.services.BooksService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Given books service impl")
@SpringBootTest(classes = {BooksServiceImpl.class})
class BooksServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private Converter<Book, BookDto> toBookDtoConverter;

    @MockBean
    private Converter<BookDto, Book> toBookConverter;

    @Autowired
    private BooksService booksService;

    @Test
    @DisplayName("should call BookRepository to find all books")
    void shouldCallBookRepositoryToFindAllBooks() {
        booksService.findAll();
        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("should call BookRepository to find book by id")
    void shouldCallBookRepositoryToFindBookById() {
        booksService.findById(1L);
        verify(bookRepository).findById(1L);
    }

    @Test
    @DisplayName("should call BookRepository to save book")
    void shouldCallBookRepositoryToCreateBook() {
        var book = new BookDto(
                1L,
                "book",
                new GenreDto(1L, "genre"),
                List.of(new AuthorDto(1L, "author")),
                List.of(new CommentDto(1L, 1L, "text"))
        );
        var expectedBook = new Book(1L, "title");

        when(toBookConverter.convert(book)).thenReturn(Optional.of(expectedBook));

        booksService.save(book);
        verify(bookRepository).save(expectedBook);
    }

    @Test
    @DisplayName("should call BookRepository to delete book by id")
    void shouldCallBookRepositoryToDeleteBookById() {
        booksService.deleteById(1L);
        verify(bookRepository).deleteById(1L);
    }
}