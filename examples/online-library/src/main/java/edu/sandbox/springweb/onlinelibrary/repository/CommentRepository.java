package edu.sandbox.springweb.onlinelibrary.repository;

import edu.sandbox.springweb.onlinelibrary.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    @Query("select new Comment(c.id, b.id, b.title, c.text) from Comment c join c.book b")
    List<Comment> findAll();

    @Override
    @Query("select new Comment(c.id, b.id, b.title, c.text) from Comment c join c.book b where c.id = :id")
    Optional<Comment> findById(@Param("id") Long id);
}
