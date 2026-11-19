package com.test.meetroomBooking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JwtResponse {

    private String token;

    private String type = "Bearer";

    private Integer id;

    private String username;

    public JwtResponse(String token, Integer ident, String login){
        this.setId(ident);
        this.setToken(token);
        this.setUsername(login);
    }

}
