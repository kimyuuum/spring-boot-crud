package com.springboot.board.service;

import java.util.List;
import javax.transaction.Transactional;

import com.springboot.board.domain.Board;
import com.springboot.board.repository.BoardRepository;

import com.springboot.board.domain.User;
import com.springboot.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<Board> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteById(Long idx){
        userRepository.deleteById(idx);
    }


}
