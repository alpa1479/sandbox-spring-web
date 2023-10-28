package edu.sandbox.springweb.onlinelibrary.converters.impl;

import edu.sandbox.springweb.onlinelibrary.converters.Converter;
import edu.sandbox.springweb.onlinelibrary.domain.Author;
import edu.sandbox.springweb.onlinelibrary.dto.AuthorDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class AuthorToAuthorDtoConverter implements Converter<Author, AuthorDto> {

    @Override
    public Optional<AuthorDto> convert(Author author) {
        return ofNullable(author).map(this::map);
    }

    private AuthorDto map(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }
}
