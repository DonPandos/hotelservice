package com.spring.hotelservice.service;

import com.spring.hotelservice.exception.UsernameAlreadyExists;
import com.spring.hotelservice.model.Status;
import com.spring.hotelservice.model.User;
import com.spring.hotelservice.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInService(UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signIn(String username,
                       String password,
                       String firstname,
                       String lastname) throws UsernameAlreadyExists {
        try {
            if (userService.findByUsername(username) != null) throw new UsernameAlreadyExists();
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setRole(roleRepository.findByRole("ROLE_USER").get());
            user.setStatus(Status.ACTIVE);
            userService.add(user);
            return user;
        } catch (UsernameAlreadyExists e) {
            throw e;
        }
    }
}
