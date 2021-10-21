package com.js.freeproject.domain.problem.domain;

import com.js.freeproject.domain.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Override
    @EntityGraph(attributePaths = {"category", "answers", "problemPicture"})
    List<Problem> findAll();

    @Query("select distinct p from Problem p " +
            "join fetch p.category c " +
            "join fetch p.answers a " +
            "join fetch p.problemPicture pi " +
            "where c.name = :name")
    List<Problem> findByCategory(@Param("name") String name);

    @EntityGraph(attributePaths = {"category", "answers", "problemPicture"})
    @Query("select p from Problem p where p.status = :status")
    List<Problem> findByStatus(@Param("status") ProblemStatus status);
}
