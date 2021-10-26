package com.js.freeproject.domain.comment.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @EntityGraph(attributePaths = {"user","parent"})
    @Query("select c from Comment c where c.board.id = :boardId")
    List<Comment> findAllByBoardId(final Long boardId);
}
