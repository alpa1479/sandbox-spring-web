package edu.sandbox.spring.mvc.onlinelibrary.services;

import edu.sandbox.spring.mvc.onlinelibrary.core.operations.CrudOperations;
import edu.sandbox.spring.mvc.onlinelibrary.dto.GenreDto;

import java.util.List;

public interface GenresService extends CrudOperations<GenreDto> {

    List<GenreDto> findAllById(Iterable<Long> ids);
}
