package com.spring.securityjwttemplate.controller;

import com.spring.securityjwttemplate.model.User;
import com.spring.securityjwttemplate.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRestController {

    private final UserRepository userRepository;

    public TestRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/s")
    public User getUsers() {
        return userRepository.getOne(1);
    }
}
