package edu.sandbox.springweb.onlinelibrary.services;

import edu.sandbox.springweb.onlinelibrary.core.operations.CrudOperations;
import edu.sandbox.springweb.onlinelibrary.dto.GenreDto;

import java.util.List;

public interface GenresService extends CrudOperations<GenreDto> {

    List<GenreDto> findAllById(Iterable<Long> ids);
}
