package com.spring.hotelservice.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "facilities")
public class Facility extends BaseEntity {
    @Column(name = "facility")
    private String name;
}
