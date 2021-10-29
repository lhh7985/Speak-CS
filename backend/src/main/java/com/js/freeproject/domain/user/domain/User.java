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

import org.hibernate.annotations.ColumnDefault;

import com.js.freeproject.domain.scorehistory.domain.ScoreHistory;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
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
    @ColumnDefault("'ROLE_USER'")
    private UserRole role;
    
    private String image;
    
    @OneToMany(mappedBy = "user")
    private List<ScoreHistory> scorehistorys;

	@Builder
    public User(String email, String nickName, String name, String pass, String image) {
    	this.email = email;
    	this.nickName = nickName;
    	this.name = name;
    	this.pass = pass;
    	this.image = image;
    }
	
    public void setPass(String pass) {
		this.pass = pass;
	}
    
    public User updateUser(final User user) {
    	this.email = user.getEmail();
    	this.image = user.getImage();
    	this.nickName = user.getNickName();
    	this.name = user.getName();
    	this.pass = user.getPass();
    	return this;
    }
}
