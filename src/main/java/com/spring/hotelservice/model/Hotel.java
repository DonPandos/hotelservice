package com.spring.hotelservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
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
    @JsonIgnore
    private String imageName;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "hotels_facility",
            joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id", referencedColumnName = "id"))
    private Set<Facility> facilities;

    @Transient
    private String image;

    @PostLoad
    public void imageInitialization() throws IOException {
        File file = new File("src/main/java/com/spring/hotelservice/files/hotelimages/" + imageName);
        image = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
    }
}
