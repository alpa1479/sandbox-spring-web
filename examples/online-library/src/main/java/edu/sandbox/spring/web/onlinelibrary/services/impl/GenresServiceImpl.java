package edu.sandbox.spring.web.onlinelibrary.services.impl;

import edu.sandbox.spring.web.onlinelibrary.converters.Converter;
import edu.sandbox.spring.web.onlinelibrary.domain.Genre;
import edu.sandbox.spring.web.onlinelibrary.dto.GenreDto;
import edu.sandbox.spring.web.onlinelibrary.repository.GenreRepository;
import edu.sandbox.spring.web.onlinelibrary.services.GenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenresServiceImpl implements GenresService {

    private final GenreRepository genreRepository;
    private final Converter<Genre, GenreDto> toGenreDtoConverter;
    private final Converter<GenreDto, Genre> toGenreConverter;

    @Override
    public List<GenreDto> findAll() {
        return toGenreDtoConverter.convert(genreRepository.findAll());
    }

    @Override
    public List<GenreDto> findAllById(Iterable<Long> ids) {
        return toGenreDtoConverter.convert(genreRepository.findAllById(ids));
    }

    @Override
    public Optional<GenreDto> findById(Long id) {
        return toGenreDtoConverter.convert(genreRepository.findById(id).orElse(null));
    }

    @Override
    public GenreDto save(GenreDto genre) {
        return toGenreConverter.convert(genre)
                .map(genreRepository::save)
                .flatMap(toGenreDtoConverter::convert)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
