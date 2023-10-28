package edu.sandbox.springweb.onlinelibrary.services;

import edu.sandbox.springweb.onlinelibrary.core.operations.CrudOperations;
import edu.sandbox.springweb.onlinelibrary.dto.BookDto;

public interface BooksService extends CrudOperations<BookDto> {

    void updateBookTitleById(Long id, String newTitle);
}
