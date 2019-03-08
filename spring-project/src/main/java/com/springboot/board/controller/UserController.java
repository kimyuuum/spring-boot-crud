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

    @RequestMapping(value = "/ushowAll", method = RequestMethod.GET)
    public List<User> showAll(){
        return userService.findAll();
    }

    @RequestMapping(value ="/ushowOne", method = RequestMethod.GET)
    public User showOne(@RequestParam (name = "idx") Long idx){
        return userService.findById(idx);
    }

    @RequestMapping(value = "/uinsert", method = RequestMethod.POST)
    // @RequestBody 어노테이션은 @RequestMapping에 의해 POST 방식으로 전송된 HTTP 요청 데이터를
    // String 타입의 body 파라미터로 전달된다.(수신)
    // 그리고 @ResponseBody 어노테이션이 @RequestMapping 메서드에서 적용되면
    // 해당 메서드의 리턴 값을 HTTP 응답 데이터로 사용한다.
    // insert 메서드의 리턴 값의 타입 데이터를 HTTP 응답 데이터로 전송한다.(송신)
    public void insert(@RequestBody User user){
        userService.saveUser(user);
    }

    @RequestMapping(value = "/udelete", method = RequestMethod.DELETE)
    public void delete(@RequestParam(name = "idx") Long idx){
        userService.deleteById(idx);
    }

    @RequestMapping(value = "/uupdate" , method = RequestMethod.POST)
    public void update(@RequestBody User request){userService.saveUser(request);}

}
