package edu.sandbox.spring.web.onlinelibrary.services.impl;

import edu.sandbox.spring.web.onlinelibrary.converters.Converter;
import edu.sandbox.spring.web.onlinelibrary.domain.Author;
import edu.sandbox.spring.web.onlinelibrary.dto.AuthorDto;
import edu.sandbox.spring.web.onlinelibrary.repository.AuthorRepository;
import edu.sandbox.spring.web.onlinelibrary.services.AuthorsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Given authors service impl")
@SpringBootTest(classes = {AuthorsServiceImpl.class})
class AuthorsServiceImplTest {

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private Converter<Author, AuthorDto> toAuthorDtoConverter;

    @MockBean
    private Converter<AuthorDto, Author> toAuthorConverter;

    @Autowired
    private AuthorsService authorsService;

    @Test
    @DisplayName("should call AuthorRepository to find all authors")
    void shouldCallAuthorRepositoryToFindAllAuthors() {
        authorsService.findAll();
        verify(authorRepository).findAll();
    }

    @Test
    @DisplayName("should call AuthorRepository to find author by id")
    void shouldCallAuthorRepositoryToFindAuthorById() {
        authorsService.findById(1L);
        verify(authorRepository).findById(1L);
    }

    @Test
    @DisplayName("should call AuthorRepository to save author")
    void shouldCallAuthorRepositoryToCreateAuthor() {
        var author = new AuthorDto(1L, "author");
        var expectedAuthor = new Author(1L, "author");

        when(toAuthorConverter.convert(author)).thenReturn(Optional.of(expectedAuthor));

        authorsService.save(author);
        verify(authorRepository).save(expectedAuthor);
    }

    @Test
    @DisplayName("should call AuthorRepository to delete author by id")
    void shouldCallAuthorRepositoryToDeleteAuthorById() {
        authorsService.deleteById(1L);
        verify(authorRepository).deleteById(1L);
    }
}