package edu.sandbox.spring.web.onlinelibrary.converters.impl;

import edu.sandbox.spring.web.onlinelibrary.converters.Converter;
import edu.sandbox.spring.web.onlinelibrary.domain.Genre;
import edu.sandbox.spring.web.onlinelibrary.dto.GenreDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class GenreDtoToGenreConverter implements Converter<GenreDto, Genre> {

    @Override
    public Optional<Genre> convert(GenreDto genre) {
        return ofNullable(genre).map(this::map);
    }

    private Genre map(GenreDto genre) {
        return new Genre(genre.getId(), genre.getName());
    }
}
