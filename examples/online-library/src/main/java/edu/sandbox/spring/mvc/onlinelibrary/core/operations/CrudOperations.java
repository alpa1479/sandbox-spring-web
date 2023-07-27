package edu.sandbox.spring.mvc.onlinelibrary.core.operations;

import java.util.List;
import java.util.Optional;

public interface CrudOperations<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T object);

    void deleteById(Long id);
}
