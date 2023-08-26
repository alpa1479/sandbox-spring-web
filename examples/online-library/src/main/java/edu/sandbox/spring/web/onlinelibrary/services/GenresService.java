package edu.sandbox.spring.web.onlinelibrary.services;

import edu.sandbox.spring.web.onlinelibrary.core.operations.CrudOperations;
import edu.sandbox.spring.web.onlinelibrary.dto.GenreDto;

import java.util.List;

public interface GenresService extends CrudOperations<GenreDto> {

    List<GenreDto> findAllById(Iterable<Long> ids);
}
