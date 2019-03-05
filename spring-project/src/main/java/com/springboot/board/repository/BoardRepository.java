package com.springboot.board.repository;


import com.springboot.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository extends JPARepository<Board,Long>{


    List<Board> findAll();

    void save(Board board);

    void deleteById(Long index);
}

