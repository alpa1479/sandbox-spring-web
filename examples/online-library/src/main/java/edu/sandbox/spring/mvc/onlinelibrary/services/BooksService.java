package edu.sandbox.spring.mvc.onlinelibrary.services;

import edu.sandbox.spring.mvc.onlinelibrary.core.operations.CrudOperations;
import edu.sandbox.spring.mvc.onlinelibrary.dto.BookDto;

public interface BooksService extends CrudOperations<BookDto> {

    void updateBookTitleById(Long id, String newTitle);
}
