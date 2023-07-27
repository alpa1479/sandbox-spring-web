package edu.sandbox.spring.mvc.onlinelibrary.controller;

import edu.sandbox.spring.mvc.onlinelibrary.dto.AuthorDto;
import edu.sandbox.spring.mvc.onlinelibrary.services.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorsService authorsService;

    @GetMapping("/authors")
    public List<AuthorDto> findAll() {
        return authorsService.findAll();
    }
}