package edu.sandbox.spring.web.onlinelibrary.services;

import edu.sandbox.spring.web.onlinelibrary.core.operations.CrudOperations;
import edu.sandbox.spring.web.onlinelibrary.dto.CommentDto;

import java.util.List;

public interface CommentsService extends CrudOperations<CommentDto> {

    List<CommentDto> findCommentsByBookId(Long id);

    void deleteAllByBookId(Long bookId);
}
