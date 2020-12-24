package com.spring.hotelservice.rest;

import com.spring.hotelservice.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(hotelService.getAll());
    }

    @GetMapping("/city/{cityId}")
   // @PreAuthorize("hasAuthority('READ:HOTELS')")
    public ResponseEntity getHotelsByCity(@PathVariable Integer cityId) {
        return ResponseEntity.ok(hotelService.getAllByCity(cityId));
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity getHotelsByCountry(@PathVariable Integer countryId) {
        return ResponseEntity.ok(hotelService.getAllByCountry(countryId));
    }
}
