package edu.sandbox.spring.web.onlinelibrary.controller;

import edu.sandbox.spring.web.onlinelibrary.dto.CommentDto;
import edu.sandbox.spring.web.onlinelibrary.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentsService commentsService;

    @PostMapping("/comments")
    public ResponseEntity<?> saveCommentToBook(@RequestBody CommentDto commentDto) {
        commentsService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/books/{id}")
    public ResponseEntity<?> deleteAllByBookId(@PathVariable Long id) {
        commentsService.deleteAllByBookId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}