package com.js.freeproject.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginRequest {
	@JsonProperty("email")
    private String email;

    @JsonProperty("pass")
    private String pass;

}
