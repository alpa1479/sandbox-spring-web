package edu.sandbox.spring.mvc.onlinelibrary.converters.impl;

import edu.sandbox.spring.mvc.onlinelibrary.converters.Converter;
import edu.sandbox.spring.mvc.onlinelibrary.domain.Author;
import edu.sandbox.spring.mvc.onlinelibrary.dto.AuthorDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class AuthorDtoToAuthorConverter implements Converter<AuthorDto, Author> {

    @Override
    public Optional<Author> convert(AuthorDto author) {
        return ofNullable(author).map(this::map);
    }

    private Author map(AuthorDto author) {
        return new Author(author.getId(), author.getName());
    }
}