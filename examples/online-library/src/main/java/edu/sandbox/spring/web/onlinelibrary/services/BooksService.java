package edu.sandbox.spring.web.onlinelibrary.services;

import edu.sandbox.spring.web.onlinelibrary.core.operations.CrudOperations;
import edu.sandbox.spring.web.onlinelibrary.dto.BookDto;

public interface BooksService extends CrudOperations<BookDto> {

    void updateBookTitleById(Long id, String newTitle);
}
