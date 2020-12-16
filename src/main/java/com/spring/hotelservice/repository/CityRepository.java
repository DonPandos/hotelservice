package com.spring.hotelservice.repository;

import com.spring.hotelservice.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

}
