package edu.sandbox.spring.mvc.onlinelibrary.repository;

import edu.sandbox.spring.mvc.onlinelibrary.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
