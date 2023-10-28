package edu.sandbox.springweb.onlinelibrary.converters.impl;

import edu.sandbox.springweb.onlinelibrary.converters.Converter;
import edu.sandbox.springweb.onlinelibrary.domain.Comment;
import edu.sandbox.springweb.onlinelibrary.dto.CommentDto;
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
