package com.springboot.board.domain;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;

import java.util.Date;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "BOARD")
public class Board {
    @Id
    @GeneratedValue
    private Long idx;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @Column(name = "uploadDate")
    @Temporal(TemporalType.DATE)
    Date uploadDate;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;


}
