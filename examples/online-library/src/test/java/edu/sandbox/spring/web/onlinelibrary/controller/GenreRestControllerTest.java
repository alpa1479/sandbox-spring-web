package edu.sandbox.spring.web.onlinelibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sandbox.spring.web.onlinelibrary.dto.GenreDto;
import edu.sandbox.spring.web.onlinelibrary.services.GenresService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GenreRestController.class)
class GenreRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GenresService genresService;

    @Test
    @DisplayName("should return all genres")
    void shouldReturnAllGenres() throws Exception {
        var genres = List.of(new GenreDto(1L, "Genre1"), new GenreDto(2L, "Genre2"));

        given(genresService.findAll()).willReturn(genres);

        mvc.perform(get("/api/v1/genres").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(genres)));
    }
}