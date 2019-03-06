package com.springboot.board.controller;


import com.springboot.board.domain.Board;
import com.springboot.board.domain.User;
import com.springboot.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/showAll",method = RequestMethod.GET)
    public List<User> showAll(){
        return userService.findAll();
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
