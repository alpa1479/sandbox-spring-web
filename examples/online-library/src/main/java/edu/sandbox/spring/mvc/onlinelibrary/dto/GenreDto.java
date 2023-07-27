package edu.sandbox.spring.mvc.onlinelibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    private Long id;
    private String name;
}
