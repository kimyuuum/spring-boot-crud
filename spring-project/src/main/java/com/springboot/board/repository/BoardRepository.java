package com.springboot.board.repository;


import com.springboot.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board , Long>{


    List<Board> findAll();

    <B extends Board> B save(B borad);

    void deleteById(Long index);
}

