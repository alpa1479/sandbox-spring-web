package edu.sandbox.spring.web.onlinelibrary.repository;

import edu.sandbox.spring.web.onlinelibrary.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
