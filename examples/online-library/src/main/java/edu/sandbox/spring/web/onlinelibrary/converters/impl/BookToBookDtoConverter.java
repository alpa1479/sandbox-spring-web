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
public class BookToBookDtoConverter implements Converter<Book, BookDto> {

    private final Converter<Genre, GenreDto> toGenreDtoConverter;
    private final Converter<Author, AuthorDto> toAuthorDtoConverter;
    private final Converter<Comment, CommentDto> toCommentDtoConverter;

    @Override
    public Optional<BookDto> convert(Book book) {
        return ofNullable(book).map(this::map);
    }

    private BookDto map(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(toGenreDtoConverter.convert(book.getGenre()).orElse(null))
                .authors(toAuthorDtoConverter.convert(book.getAuthors()))
                .comments(toCommentDtoConverter.convert(book.getComments()))
                .build();
    }
}
