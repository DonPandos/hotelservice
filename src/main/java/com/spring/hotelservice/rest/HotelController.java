package com.spring.hotelservice.rest;

import com.spring.hotelservice.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ:HOTELS')")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(hotelService.getAll());
    }
}
