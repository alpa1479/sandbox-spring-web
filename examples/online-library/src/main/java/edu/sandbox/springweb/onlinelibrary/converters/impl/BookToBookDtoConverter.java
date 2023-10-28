package edu.sandbox.springweb.onlinelibrary.converters.impl;

import edu.sandbox.springweb.onlinelibrary.converters.Converter;
import edu.sandbox.springweb.onlinelibrary.domain.Author;
import edu.sandbox.springweb.onlinelibrary.domain.Book;
import edu.sandbox.springweb.onlinelibrary.domain.Comment;
import edu.sandbox.springweb.onlinelibrary.domain.Genre;
import edu.sandbox.springweb.onlinelibrary.dto.AuthorDto;
import edu.sandbox.springweb.onlinelibrary.dto.BookDto;
import edu.sandbox.springweb.onlinelibrary.dto.CommentDto;
import edu.sandbox.springweb.onlinelibrary.dto.GenreDto;
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
