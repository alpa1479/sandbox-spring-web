package edu.sandbox.springweb.onlinelibrary.services.impl;

import edu.sandbox.springweb.onlinelibrary.converters.Converter;
import edu.sandbox.springweb.onlinelibrary.domain.Comment;
import edu.sandbox.springweb.onlinelibrary.dto.CommentDto;
import edu.sandbox.springweb.onlinelibrary.repository.BookRepository;
import edu.sandbox.springweb.onlinelibrary.repository.CommentRepository;
import edu.sandbox.springweb.onlinelibrary.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final Converter<Comment, CommentDto> toCommentDtoConverter;
    private final Converter<CommentDto, Comment> toCommentConverter;

    @Override
    public List<CommentDto> findAll() {
        return toCommentDtoConverter.convert(commentRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findCommentsByBookId(Long id) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            var comments = book.get().getComments();
            Hibernate.initialize(comments);
            return toCommentDtoConverter.convert(comments);
        }
        return emptyList();
    }

    @Override
    public Optional<CommentDto> findById(Long id) {
        return toCommentDtoConverter.convert(commentRepository.findById(id).orElse(null));
    }

    @Override
    public CommentDto save(CommentDto comment) {
        return toCommentConverter.convert(comment)
                .map(commentRepository::save)
                .flatMap(toCommentDtoConverter::convert)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAllByBookId(Long bookId) {
        var book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            var comments = book.get().getComments();
            Hibernate.initialize(comments);
            var commentIds = comments.stream().map(Comment::getId).toList();
            commentRepository.deleteAllById(commentIds);
        }
    }
}
