package com.springboot.board.repository;


import com.springboot.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends JPARepository<Board,Long>{


}

