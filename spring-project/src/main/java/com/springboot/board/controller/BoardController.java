package com.springboot.board.controller;


import com.springboot.board.service.BoardService;
import com.springboot.board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.List;

@RequestMapping("/board")
@RestController
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public List<Board> showAll(){return boardService.findAll();}

    @RequestMapping(value = "/showOne" ,method = RequestMethod.GET)
    public Board showOne(Long idx){return boardService.findById(idx);}

    @RequestMapping(value = "/binsert",method = RequestMethod.POST)
    public void insert(@RequestBody Board board){
        boardService.saveBoard(board);
    }

    @RequestMapping(value = "/bupdate",method = RequestMethod.POST)
    public void update(@RequestBody Board request){
        boardService.saveBoard(request);
    }

    @RequestMapping(value = "/bdelete",method = RequestMethod.GET)
    public void delete(@RequestParam (name = "idx") Long idx){
        boardService.deleteById(idx);
    }

}
