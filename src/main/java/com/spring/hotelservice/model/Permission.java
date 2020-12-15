package com.spring.hotelservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity{

    @Column(name = "permission")
    private String permission;

}
