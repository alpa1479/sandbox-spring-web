package edu.sandbox.springweb.onlinelibrary.repository;

import edu.sandbox.springweb.onlinelibrary.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
