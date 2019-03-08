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
@Getter @Setter
@Table(name = "USER")
public class User{
    @Id
    @GeneratedValue
    private Long idx;

//불필요한 조회를 막으려면 LAZY 사용 항상 가져오면 EAGER
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Board> boards = new ArrayList<>();

    private String username;
    private String userid;
    @Column(name = "phone_num")
    private String phoneNum;

    public void addBoard(Board board){
        this.boards.add(board);
    }

    public void removeBoard(Board board){
        this.boards.remove(board);
    }

    public Long getUseridx() {
        return idx;
    }
}
