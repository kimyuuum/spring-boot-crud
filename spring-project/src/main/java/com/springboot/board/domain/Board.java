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
public class Board {

    @Id
    @GeneratedValue
    private Long index;

    @Temporal(TemporalType.DATE)
    Date uploadDate;

    @ManyToOne
//  @JoinColumn(name = "userid")
    private User user;

   private String title;
   private String contents;



}
