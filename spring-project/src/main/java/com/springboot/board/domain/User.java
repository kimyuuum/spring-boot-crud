package com.springboot.board.domain;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long idx;

//불필요한 조회를 막으려면 LAZY 사용 항상 가져오면 EAGER
    @OneToMany(mappedBy = "User",cascade = CascadeType.ALL)
    private List<Board> boards;

    private String username;
    private String userid;
    @Column(name = "phone_num")
    private String phoneNum;


    public boolean addBoard(Board board){
        if(boards ==null)
            boards = new ArrayList<>();

        return this.boards.add(board);
    }




}
