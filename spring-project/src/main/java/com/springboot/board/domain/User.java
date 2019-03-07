package com.springboot.board.domain;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Getter @Setter
@Table(name = "USER")
public class User{
    @Id
    @GeneratedValue
    private Long idx;

//불필요한 조회를 막으려면 LAZY 사용 항상 가져오면 EAGER
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Board> boards;

    private String username;
    private String userid;
    @Column(name = "phone_num")
    private String phoneNum;

    public Long getUseridx(){return idx;}
    public String getUserid(){
        return userid;
    }
    public void setUserid(String userid){
        this.userid = userid;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }


    public boolean addBoard(Board board){
        if(boards ==null)
            boards = new ArrayList<>();

        return this.boards.add(board);
    }

}
