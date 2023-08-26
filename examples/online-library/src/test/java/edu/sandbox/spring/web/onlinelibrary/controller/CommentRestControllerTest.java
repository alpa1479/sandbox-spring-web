package edu.sandbox.spring.web.onlinelibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sandbox.spring.web.onlinelibrary.dto.CommentDto;
import edu.sandbox.spring.web.onlinelibrary.services.CommentsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentRestController.class)
class CommentRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CommentsService commentsService;

    @Test
    @DisplayName("should add a comment to book")
    void shouldAddCommentToBook() throws Exception {
        var commentDto = new CommentDto(1L, 1L, "commentText");

        mvc.perform(post("/api/v1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commentDto)))
                .andExpect(status().isCreated());

        verify(commentsService).save(commentDto);
        verifyNoMoreInteractions(commentsService);
    }

    @Test
    @DisplayName("should delete all comments for book")
    void shouldDeleteAllCommentsForBook() throws Exception {
        var bookId = 1L;

        mvc.perform(delete("/api/v1/comments/books/{id}", bookId))
                .andExpect(status().isNoContent());

        verify(commentsService).deleteAllByBookId(bookId);
        verifyNoMoreInteractions(commentsService);
    }
}