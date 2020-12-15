package com.spring.hotelservice.rest;

import com.spring.hotelservice.dto.signin.SignInRequestDTO;
import com.spring.hotelservice.exception.UsernameAlreadyExists;
import com.spring.hotelservice.model.User;
import com.spring.hotelservice.service.SignInService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signin")
public class SignInController {

    private final SignInService signInService;

    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @PostMapping
    public ResponseEntity signIn(@RequestBody SignInRequestDTO request) {
        try {
            User user = signInService.signIn(
                    request.getUsername(),
                    request.getPassword(),
                    request.getFirstname(),
                    request.getLastname()
            );
            return ResponseEntity.ok(user);
        } catch (UsernameAlreadyExists e) {
            return new ResponseEntity("Username already exists", HttpStatus.FORBIDDEN);
        }
    }

}
