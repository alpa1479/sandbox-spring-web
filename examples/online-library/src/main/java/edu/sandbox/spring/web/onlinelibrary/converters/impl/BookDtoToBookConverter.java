package edu.sandbox.spring.web.onlinelibrary.converters.impl;

import edu.sandbox.spring.web.onlinelibrary.converters.Converter;
import edu.sandbox.spring.web.onlinelibrary.domain.Author;
import edu.sandbox.spring.web.onlinelibrary.domain.Book;
import edu.sandbox.spring.web.onlinelibrary.domain.Comment;
import edu.sandbox.spring.web.onlinelibrary.domain.Genre;
import edu.sandbox.spring.web.onlinelibrary.dto.AuthorDto;
import edu.sandbox.spring.web.onlinelibrary.dto.BookDto;
import edu.sandbox.spring.web.onlinelibrary.dto.CommentDto;
import edu.sandbox.spring.web.onlinelibrary.dto.GenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class BookDtoToBookConverter implements Converter<BookDto, Book> {

    private final Converter<GenreDto, Genre> toGenreConverter;
    private final Converter<AuthorDto, Author> toAuthorConverter;
    private final Converter<CommentDto, Comment> toCommentConverter;

    @Override
    public Optional<Book> convert(BookDto book) {
        return ofNullable(book).map(this::map);
    }

    private Book map(BookDto book) {
        return Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(toGenreConverter.convert(book.getGenre()).orElse(null))
                .authors(toAuthorConverter.convert(book.getAuthors()))
                .comments(toCommentConverter.convert(book.getComments()))
                .build();
    }
}
