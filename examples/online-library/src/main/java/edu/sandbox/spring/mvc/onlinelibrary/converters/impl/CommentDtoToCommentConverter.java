package edu.sandbox.spring.mvc.onlinelibrary.converters.impl;

import edu.sandbox.spring.mvc.onlinelibrary.converters.Converter;
import edu.sandbox.spring.mvc.onlinelibrary.domain.Comment;
import edu.sandbox.spring.mvc.onlinelibrary.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class CommentDtoToCommentConverter implements Converter<CommentDto, Comment> {

    @Override
    public Optional<Comment> convert(CommentDto comment) {
        return ofNullable(comment).map(this::map);
    }

    private Comment map(CommentDto comment) {
        return new Comment(comment.getId(), comment.getBookId(), comment.getText());
    }
}
