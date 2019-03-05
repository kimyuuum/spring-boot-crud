package com.springboot.board.service;

import java.util.List;
import javax.transaction.Transactional;

import com.springboot.board.domain.Board;


import com.springboot.board.domain.User;
import com.springboot.board.repository.BoardRepository;
import com.springboot.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;

    }

    public List<Board> findAll(){return boardRepository.findAll();}

    public void saveBoard(Board board){boardRepository.save(board);}
    public void deleteById(Long index){
        boardRepository.deleteById(index);
    }


}