package com.spring.hotelservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "countries")
public class Country extends BaseEntity {
    @Column(name = "country")
    private String name;

}
