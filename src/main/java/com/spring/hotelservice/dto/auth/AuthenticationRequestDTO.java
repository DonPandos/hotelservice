package com.spring.hotelservice.dto.auth;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {

    private String username;
    private String password;
}
