package edu.sandbox.springweb.onlinelibrary.services.impl;

import edu.sandbox.springweb.onlinelibrary.converters.Converter;
import edu.sandbox.springweb.onlinelibrary.domain.Comment;
import edu.sandbox.springweb.onlinelibrary.dto.CommentDto;
import edu.sandbox.springweb.onlinelibrary.repository.BookRepository;
import edu.sandbox.springweb.onlinelibrary.repository.CommentRepository;
import edu.sandbox.springweb.onlinelibrary.services.CommentsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Given comments service impl")
@SpringBootTest(classes = {CommentsServiceImpl.class})
class CommentsServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private Converter<Comment, CommentDto> toCommentDtoConverter;

    @MockBean
    private Converter<CommentDto, Comment> toCommentConverter;

    @Autowired
    private CommentsService commentsService;

    @Test
    @DisplayName("should call CommentRepository to find all comments")
    void shouldCallCommentRepositoryToFindAllAuthors() {
        commentsService.findAll();
        verify(commentRepository).findAll();
    }

    @Test
    @DisplayName("should call CommentRepository to find comment by id")
    void shouldCallCommentRepositoryToFindAuthorById() {
        commentsService.findById(1L);
        verify(commentRepository).findById(1L);
    }

    @Test
    @DisplayName("should call BookRepository to find comments by book id")
    void shouldCallBookRepositoryToFindCommentsByBookId() {
        commentsService.findCommentsByBookId(1L);
        verify(bookRepository).findById(1L);
    }

    @Test
    @DisplayName("should call CommentRepository to save comment")
    void shouldCallCommentRepositoryToCreateAuthor() {
        var comment = new CommentDto(1L, 1L, "new comment");
        var expectedComment = new Comment(1L, "new comment");

        when(toCommentConverter.convert(comment)).thenReturn(Optional.of(expectedComment));

        commentsService.save(comment);
        verify(commentRepository).save(expectedComment);
    }

    @Test
    @DisplayName("should call CommentRepository to delete comment by id")
    void shouldCallCommentRepositoryToDeleteAuthorById() {
        commentsService.deleteById(1L);
        verify(commentRepository).deleteById(1L);
    }
}