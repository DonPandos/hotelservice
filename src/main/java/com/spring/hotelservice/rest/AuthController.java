package com.spring.hotelservice.rest;

import com.spring.hotelservice.dto.auth.AuthenticationRequestDTO;
import com.spring.hotelservice.dto.signin.SignInRequestDTO;
import com.spring.hotelservice.exception.UsernameAlreadyExists;
import com.spring.hotelservice.model.User;
import com.spring.hotelservice.repository.UserRepository;
import com.spring.hotelservice.security.jwt.JwtTokenProvider;
import com.spring.hotelservice.service.SignInService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final SignInService signInService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, SignInService signInService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.signInService = signInService;
    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found."));
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole().getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("firstname", user.getFirstname());
            response.put("lastname", user.getLastname());
            response.put("token", token);

            return ResponseEntity.ok().body(response);

        } catch (AuthenticationException e) {
            Map<Object, Object> response = new HashMap<>();
            response.put("message", "Username login or password incorrect");
            return new ResponseEntity(response, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody SignInRequestDTO request) {
        try {
            User user = signInService.signIn(
                    request.getUsername(),
                    request.getPassword(),
                    request.getFirstname(),
                    request.getLastname()
            );
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole().getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("firstname", user.getFirstname());
            response.put("lastname", user.getLastname());
            response.put("token", token);
            return ResponseEntity.ok(user);
        } catch (UsernameAlreadyExists e) {
            Map<Object, Object> response = new HashMap<>();
            response.put("message", "Username already exists");
            return new ResponseEntity(response, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
