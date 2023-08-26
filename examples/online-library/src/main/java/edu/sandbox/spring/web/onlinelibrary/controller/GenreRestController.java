package edu.sandbox.spring.web.onlinelibrary.controller;

import edu.sandbox.spring.web.onlinelibrary.dto.GenreDto;
import edu.sandbox.spring.web.onlinelibrary.services.GenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GenreRestController {

    private final GenresService genresService;

    @GetMapping("/genres")
    public List<GenreDto> findAll() {
        return genresService.findAll();
    }
}