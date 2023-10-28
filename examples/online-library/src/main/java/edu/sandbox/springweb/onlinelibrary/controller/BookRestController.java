package edu.sandbox.springweb.onlinelibrary.controller;

import edu.sandbox.springweb.onlinelibrary.dto.BookDto;
import edu.sandbox.springweb.onlinelibrary.exceptions.NotFoundException;
import edu.sandbox.springweb.onlinelibrary.services.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookRestController {

    private final BooksService booksService;

    @GetMapping("/books")
    public List<BookDto> findAll() {
        return booksService.findAll();
    }

    @GetMapping("/books/{id}")
    public BookDto findById(@PathVariable Long id) {
        return booksService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/books")
    public ResponseEntity<?> create(@RequestBody BookDto book) {
        booksService.save(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/books")
    public ResponseEntity<?> update(@RequestBody BookDto book) {
        booksService.save(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        booksService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}