package com.spring.hotelservice.rest;

import com.spring.hotelservice.model.Role;
import com.spring.hotelservice.repository.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Integer id) {
        return roleRepository.findById(id).get();
    }
}
