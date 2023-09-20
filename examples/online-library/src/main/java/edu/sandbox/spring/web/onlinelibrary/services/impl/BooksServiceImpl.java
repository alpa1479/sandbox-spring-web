package edu.sandbox.spring.web.onlinelibrary.services.impl;

import edu.sandbox.spring.web.onlinelibrary.converters.Converter;
import edu.sandbox.spring.web.onlinelibrary.domain.Book;
import edu.sandbox.spring.web.onlinelibrary.dto.BookDto;
import edu.sandbox.spring.web.onlinelibrary.repository.BookRepository;
import edu.sandbox.spring.web.onlinelibrary.services.BooksService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;
    private final Converter<Book, BookDto> toBookDtoConverter;
    private final Converter<BookDto, Book> toBookConverter;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        var books = bookRepository.findAll();
        if (!isEmpty(books)) {
            books.stream().map(Book::getAuthors).forEach(Hibernate::initialize);
        }
        return toBookDtoConverter.convert(books);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookDto> findById(Long id) {
        var book = bookRepository.findById(id);
        book.ifPresent(b -> Hibernate.initialize(b.getAuthors()));
        return toBookDtoConverter.convert(book.orElse(null));
    }

    @Override
    public BookDto save(BookDto book) {
        return toBookConverter.convert(book)
                .map(bookRepository::save)
                .flatMap(toBookDtoConverter::convert)
                .orElse(null);
    }

    @Override
    @Transactional
    public void updateBookTitleById(Long id, String newTitle) {
        bookRepository.findById(id).ifPresent(b -> b.setTitle(newTitle));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
