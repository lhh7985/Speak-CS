package com.js.freeproject.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {
    public String message;
    
    public CommonResponse() {}
    
    public CommonResponse(String message) {
    	this.message = message;
    }
    
    public static CommonResponse of(String message) { 
    	 CommonResponse commonResponse = new CommonResponse();
    	 commonResponse.message = message;
    	 return commonResponse; 
    }
}
