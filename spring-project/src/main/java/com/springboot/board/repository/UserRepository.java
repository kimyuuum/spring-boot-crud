package com.springboot.board.repository;


import com.springboot.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JPARepository<User,Long>{

}

