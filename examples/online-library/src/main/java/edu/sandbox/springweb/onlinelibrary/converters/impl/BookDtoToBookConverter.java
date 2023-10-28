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
public class BookDtoToBookConverter implements Converter<BookDto, Book> {

    private final Converter<GenreDto, Genre> toGenreConverter;
    private final Converter<AuthorDto, Author> toAuthorConverter;
    private final Converter<CommentDto, Comment> toCommentConverter;

    @Override
    public Optional<Book> convert(BookDto book) {
        return ofNullable(book).map(this::map);
    }

    private Book map(BookDto book) {
        Book book1 = Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(toGenreConverter.convert(book.getGenre()).orElse(null))
                .authors(toAuthorConverter.convert(book.getAuthors()))
                .comments(toCommentConverter.convert(book.getComments()))
                .build();
        book1.getComments().forEach(comment -> comment.setBook(book1));
        return book1;
    }
}
