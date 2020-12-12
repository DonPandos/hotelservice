package com.spring.securityjwttemplate.repository;

import com.spring.securityjwttemplate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
