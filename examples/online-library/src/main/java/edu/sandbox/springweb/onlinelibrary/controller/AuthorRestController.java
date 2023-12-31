package edu.sandbox.springweb.onlinelibrary.controller;

import edu.sandbox.springweb.onlinelibrary.controller.parameters.Pagination;
import edu.sandbox.springweb.onlinelibrary.dto.AuthorDto;
import edu.sandbox.springweb.onlinelibrary.services.AuthorsService;
import jakarta.validation.Valid;
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

    // todo(alpa1479): add pagination to other layers
    @GetMapping("/authors")
    public List<AuthorDto> findAll(@Valid Pagination pagination) {
        return authorsService.findAll();
    }
}