package com.spring.hotelservice.repository;

import com.spring.hotelservice.model.City;
import com.spring.hotelservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findAll();
    List<Hotel> findAllByCity(City city);

    @Query(value = "SELECT * FROM hote  ls h " +
            "JOIN cities c ON h.city_id = c.id " +
            "WHERE c.country_id = ?1", nativeQuery = true)
    List<Hotel> findAllByCountry(Integer countryId);

    @Query(value = "SELECT h.id, h.name, h.adress, h.description, h.image_name, h.rating, h.city_id FROM hotels h\n" +
            "INNER JOIN hotels_facility hf \n" +
            "\tON h.id = hf.hotel_id\n" +
            "WHERE facility_id IN :facilitiesId\n" +
            "GROUP BY h.id\n" +
            "HAVING COUNT (DISTINCT facility_id) = :facilitiesCount", nativeQuery = true)
    List<Hotel> findAllByFacilities(@Param("facilitiesId")Set<Integer> facilitesId, @Param("facilitiesCount") Integer facilitiesCount);
}

