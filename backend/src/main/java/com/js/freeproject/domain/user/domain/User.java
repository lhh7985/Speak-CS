package com.js.freeproject.domain.user.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.js.freeproject.domain.scorehistory.domain.ScoreHistory;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String nickName;
    private String name;
    private String pass;
    
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    private UserRole role;
    
    private String image;
    
    @OneToMany(mappedBy = "user")
    private List<ScoreHistory> scorehistorys;

	@Builder
    private User(String email, String nickName, String name, String pass) {
    	this.email = email;
    	this.nickName = nickName;
    	this.name = name;
    }
	
    public void setPass(String pass) {
		this.pass = pass;
	}
}
