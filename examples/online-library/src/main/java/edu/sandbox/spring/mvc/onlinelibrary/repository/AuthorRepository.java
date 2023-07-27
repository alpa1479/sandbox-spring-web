package edu.sandbox.spring.mvc.onlinelibrary.repository;

import edu.sandbox.spring.mvc.onlinelibrary.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
