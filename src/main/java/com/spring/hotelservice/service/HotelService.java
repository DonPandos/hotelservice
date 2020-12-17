package com.spring.hotelservice.service;

import com.spring.hotelservice.model.Hotel;
import com.spring.hotelservice.repository.CityRepository;
import com.spring.hotelservice.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;

    public HotelService(HotelRepository hotelRepository, CityRepository cityRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
    }

    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    public List<Hotel> getAllByCity(Integer cityId) {
        return hotelRepository.findAllByCity(cityRepository.findById(cityId).get());
    }

    public List<Hotel> getAllByCountry(Integer countryId) {
        return hotelRepository.findAllByCountry(countryId);
    }

    public List<Hotel> getAllByFacilities(Integer ...facilityId) {

        return null;
    }
}
