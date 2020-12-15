package com.spring.hotelservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "hotels")
public class Hotel extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "adress")
    private String adress;

    @Column(name = "description")
    private String description;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "hotels_facility",
            joinColumns = @JoinColumn(name = "facility_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"))
    private Set<Facility> facilities;
}
