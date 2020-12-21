package com.spring.hotelservice.repository;

import com.spring.hotelservice.model.City;
import com.spring.hotelservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findAll();
    List<Hotel> findAllByCity(City city);

    @Query(value = "SELECT * FROM hote  ls h " +
            "JOIN cities c ON h.city_id = c.id " +
            "WHERE c.country_id = ?1", nativeQuery = true)
    List<Hotel> findAllByCountry(Integer countryId);
}

