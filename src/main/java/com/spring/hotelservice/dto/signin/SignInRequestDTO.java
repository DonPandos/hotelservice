package com.spring.hotelservice.dto.signin;

import lombok.Data;

@Data
public class SignInRequestDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
