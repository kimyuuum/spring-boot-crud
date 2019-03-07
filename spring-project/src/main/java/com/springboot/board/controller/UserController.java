package com.springboot.board.controller;


import com.springboot.board.domain.Board;
import com.springboot.board.domain.User;
import com.springboot.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.persistence.JoinColumn;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.util.List;
import java.util.Optional;

@RequestMapping("/board")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public List<User> showAll(){
        return userService.findAll();
    }

    @RequestMapping(value ="/showOne", method = RequestMethod.GET)
    public User showOne(@RequestParam (name = "idx") Long idx){
        return userService.findById(idx);
    }

    @RequestMapping(value = "/uinsert", method = RequestMethod.POST)
    public void insert(@RequestBody User user){
        userService.saveUser(user);
    }

    @RequestMapping(value = "/udelete", method = RequestMethod.DELETE)
    public void delete(@RequestParam(name = "idx") Long idx){
        userService.deleteById(idx);
    }



}
