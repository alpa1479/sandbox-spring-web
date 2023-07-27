package edu.sandbox.spring.mvc.onlinelibrary.converters.impl;

import edu.sandbox.spring.mvc.onlinelibrary.converters.Converter;
import edu.sandbox.spring.mvc.onlinelibrary.domain.Genre;
import edu.sandbox.spring.mvc.onlinelibrary.dto.GenreDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class GenreToGenreDtoConverter implements Converter<Genre, GenreDto> {

    @Override
    public Optional<GenreDto> convert(Genre genre) {
        return ofNullable(genre).map(this::map);
    }

    private GenreDto map(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
