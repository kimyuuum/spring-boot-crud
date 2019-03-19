package com.springboot.board.controller;


import com.springboot.board.domain.User;
import com.springboot.board.service.BoardService;
import com.springboot.board.domain.Board;
import com.springboot.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.RequestingUserName;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequestMapping("/board")
@RestController
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @Autowired
    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @RequestMapping(value = "/bshowAll", method = RequestMethod.GET)
    public List<Board> bshowAll(){
        return boardService.findAll();
    }

    @RequestMapping(value = "/bshowOne", method = RequestMethod.GET)
    public Board showOne(@RequestParam(name = "idx") Long idx){
        return boardService.findById(idx);
    }

    @RequestMapping(value = "/binsert", method = RequestMethod.POST)
    public void insert(@RequestBody Board board){boardService.saveBoard(board);}

    @RequestMapping(value = "/bupdate", method = RequestMethod.POST)
    public void update(@RequestBody Board request){boardService.saveBoard(request);}

    @RequestMapping(value = "/bdelete", method = RequestMethod.DELETE)
    public void delete(@RequestParam(name = "idx") Long idx){
        boardService.deleteById(idx);
    }

}
