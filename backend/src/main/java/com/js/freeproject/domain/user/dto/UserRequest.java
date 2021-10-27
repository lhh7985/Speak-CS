package com.js.freeproject.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserRequest {
    private String email;
    private String nickName;
    private String name;
    private String pass;
    private String image;
}
