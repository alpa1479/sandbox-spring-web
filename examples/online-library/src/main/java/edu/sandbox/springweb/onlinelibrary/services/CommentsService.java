package edu.sandbox.springweb.onlinelibrary.services;

import edu.sandbox.springweb.onlinelibrary.core.operations.CrudOperations;
import edu.sandbox.springweb.onlinelibrary.dto.CommentDto;

import java.util.List;

public interface CommentsService extends CrudOperations<CommentDto> {

    List<CommentDto> findCommentsByBookId(Long id);

    void deleteAllByBookId(Long bookId);
}
