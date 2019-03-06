package com.springboot.board.repository;


import com.springboot.board.domain.Board;
import com.springboot.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long>{

    <U extends User> U save(U user);
    void deleteById(Long idx);

    List<User> findAll();
}

