package com.js.freeproject.domain.problem.domain;

import com.js.freeproject.domain.user.domain.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Override
    @EntityGraph(attributePaths = {"category", "answers"})
    @Query("select p from Problem p where p.id = :id")
    Optional<Problem> findById(@Param("id") Long id);

    @EntityGraph(attributePaths = {"category"})
    @Query("select p from Problem p where p.description = :description")
    List<Problem> findByDescription (@Param("description") String description);

    @Override
    @EntityGraph(attributePaths = {"category", "problemPicture"})
    List<Problem> findAll();

    @EntityGraph(attributePaths = {"category", "user", "problemPicture"})
    @Query("select p from Problem p where p.category.name = :name and p.status = 'accept'")
    List<Problem> findByCategory(@Param("name") String name, Pageable pageable);

    @EntityGraph(attributePaths = {"category", "answers", "user"})
    @Query("select p from Problem p where p.status = :status")
    List<Problem> findByStatus(@Param("status") ProblemStatus status);



}
