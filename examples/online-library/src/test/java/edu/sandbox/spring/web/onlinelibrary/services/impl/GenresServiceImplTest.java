package edu.sandbox.spring.web.onlinelibrary.services.impl;

import edu.sandbox.spring.web.onlinelibrary.converters.Converter;
import edu.sandbox.spring.web.onlinelibrary.domain.Genre;
import edu.sandbox.spring.web.onlinelibrary.dto.GenreDto;
import edu.sandbox.spring.web.onlinelibrary.repository.GenreRepository;
import edu.sandbox.spring.web.onlinelibrary.services.GenresService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Given genres service impl")
@SpringBootTest(classes = {GenresServiceImpl.class})
class GenresServiceImplTest {

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private Converter<Genre, GenreDto> toGenreDtoConverter;

    @MockBean
    private Converter<GenreDto, Genre> toGenreConverter;

    @Autowired
    private GenresService genresService;

    @Test
    @DisplayName("should call GenreRepository to find all genres")
    void shouldCallGenreRepositoryToFindAllGenres() {
        genresService.findAll();
        verify(genreRepository).findAll();
    }

    @Test
    @DisplayName("should call GenreRepository to find genre by id")
    void shouldCallGenreRepositoryToFindGenreById() {
        genresService.findById(1L);
        verify(genreRepository).findById(1L);
    }

    @Test
    @DisplayName("should call GenreRepository to save genre")
    void shouldCallGenreRepositoryToCreateGenre() {
        var genre = new GenreDto(1L, "genre");
        var expectedGenre = new Genre(1L, "genre");

        when(toGenreConverter.convert(genre)).thenReturn(Optional.of(expectedGenre));

        genresService.save(genre);
        verify(genreRepository).save(expectedGenre);
    }

    @Test
    @DisplayName("should call GenreRepository to delete genre by id")
    void shouldCallGenreRepositoryToDeleteGenreById() {
        genresService.deleteById(1L);
        verify(genreRepository).deleteById(1L);
    }
}