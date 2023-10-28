package edu.sandbox.springweb.onlinelibrary.repository;

import edu.sandbox.springweb.onlinelibrary.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
