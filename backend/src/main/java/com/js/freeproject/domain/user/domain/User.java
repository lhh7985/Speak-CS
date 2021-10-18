package com.js.freeproject.domain.user.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.js.freeproject.domain.scorehistory.domain.ScoreHistory;

import lombok.Getter;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String nickName;
    private String name;
    private String pass;
    private String role;
    private String image;
    
    @OneToMany(mappedBy = "user")
    private List<ScoreHistory> scorehistorys;
}
