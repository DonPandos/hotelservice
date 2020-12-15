package com.spring.hotelservice.rest;

import com.spring.hotelservice.model.User;
import com.spring.hotelservice.repository.UserRepository;
import com.spring.hotelservice.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity getInfoAboutUser(@RequestHeader("Authorization") String token) {
        try {
            User user = userRepository.findByUsername(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
            return ResponseEntity.ok(user);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity("Username not found.", HttpStatus.FORBIDDEN);
        }
    }

}
