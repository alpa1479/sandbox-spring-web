package edu.sandbox.springweb.onlinelibrary.converters.impl;

import edu.sandbox.springweb.onlinelibrary.converters.Converter;
import edu.sandbox.springweb.onlinelibrary.domain.Comment;
import edu.sandbox.springweb.onlinelibrary.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class CommentToCommentDtoConverter implements Converter<Comment, CommentDto> {

    @Override
    public Optional<CommentDto> convert(Comment comment) {
        return ofNullable(comment).map(this::map);
    }

    private CommentDto map(Comment comment) {
        return new CommentDto(comment.getId(), comment.getBookId(), comment.getText());
    }
}
