package edu.sandbox.spring.mvc.onlinelibrary.services;

import edu.sandbox.spring.mvc.onlinelibrary.core.operations.CrudOperations;
import edu.sandbox.spring.mvc.onlinelibrary.dto.CommentDto;

import java.util.List;

public interface CommentsService extends CrudOperations<CommentDto> {

    List<CommentDto> findCommentsByBookId(Long id);

    void deleteAllByBookId(Long bookId);
}
