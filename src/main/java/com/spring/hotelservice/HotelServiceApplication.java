package com.spring.hotelservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HotelServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(HotelServiceApplication.class)
				.run(args);
	}

}
