package edu.sandbox.springweb.onlinelibrary.services.impl;

import edu.sandbox.springweb.onlinelibrary.converters.Converter;
import edu.sandbox.springweb.onlinelibrary.domain.Author;
import edu.sandbox.springweb.onlinelibrary.dto.AuthorDto;
import edu.sandbox.springweb.onlinelibrary.repository.AuthorRepository;
import edu.sandbox.springweb.onlinelibrary.services.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorRepository authorRepository;
    private final Converter<Author, AuthorDto> toAuthorDtoConverter;
    private final Converter<AuthorDto, Author> toAuthorConverter;

    @Override
    public List<AuthorDto> findAll() {
        return toAuthorDtoConverter.convert(authorRepository.findAll());
    }

    @Override
    public Optional<AuthorDto> findById(Long id) {
        return toAuthorDtoConverter.convert(authorRepository.findById(id).orElse(null));
    }

    @Override
    public AuthorDto save(AuthorDto author) {
        return toAuthorConverter.convert(author)
                .map(authorRepository::save)
                .flatMap(toAuthorDtoConverter::convert)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
