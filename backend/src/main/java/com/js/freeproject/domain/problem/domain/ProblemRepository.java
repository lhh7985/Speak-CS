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
    @EntityGraph(attributePaths = {"category"})
    List<Problem> findAll();

    @EntityGraph(attributePaths = {"category"})
    @Query("select p from Problem p left join p.category c where c.name = :name")
    List<Problem> findByCategory(@Param("name")String name);
}
