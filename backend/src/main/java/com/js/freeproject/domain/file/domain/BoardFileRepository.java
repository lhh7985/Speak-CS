package com.js.freeproject.domain.file.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardFileRepository extends JpaRepository<BoardFile,Long> {
    List<BoardFile> findAllByBoardId(final Long boardId);
}
