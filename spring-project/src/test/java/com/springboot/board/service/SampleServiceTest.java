package com.springboot.board.service;

import com.springboot.board.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {
    @Autowired
    private UserService userService;

    private User user;
    //testclass 메서드들이 테스트되기 전에 실행되도록 지정하는 annotation
    @Before
    public void setup(){
        user = new User();
        user.setUserid("kakaopay");
        user.setUsername("kimyumin");
        user.setPhoneNum("010-9993-9999");

    }

    @Test
    public void findAll(){
        assertThat(userService.findAll().size()).isNotNull();

    }

    @Test
    public void insert(){
        userService.saveUser(user);
        assertThat(userService.findById(user.getUseridx()).getUsername()).isEqualTo("kimyumin");
    }

    @Test
    public void getOne(){
        System.out.println(user.getUseridx());
        System.out.println("dddd");
        assertThat(userService.findById(4L).getUseridx()).isEqualTo(4L);
    }

    @Test
    public void update(){
        User updateuser = userService.findById(4L);
        updateuser.setUsername("upadate");
        userService.saveUser(updateuser);
        assertThat(userService.findById(updateuser.getUseridx()).getUsername()).isEqualTo("update");
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteOne() {
        // 하나의 데이터 삭제
        userService.deleteById(4L);
        assertThat(userService.findById(4L)).isNull();
    }
}

