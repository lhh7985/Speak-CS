package com.js.freeproject.domain.board.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @EntityGraph(attributePaths = {"comments"})
//    @Query("select b from Board b where b.id = :id")
    Optional<Board> findById(Long id);

    @EntityGraph(attributePaths = {"user"})
    List<Board> findAll();
}
