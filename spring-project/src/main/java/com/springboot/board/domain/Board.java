package com.springboot.board.domain;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;

import java.util.Date;
import javax.persistence.*;
public class Board {
    @Temporal(TemporalType.DATE)
    Date uploadDate;

    @Id
    @GeneratedValue
    private Long index;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private User user;

   private String title;
   private String contents;



}
