package com.spring.hotelservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private Status status;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    @JsonIgnore
    private Role role;
}
