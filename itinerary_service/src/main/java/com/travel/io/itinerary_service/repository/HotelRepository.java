package com.travel.io.itinerary_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.io.itinerary_service.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

    public Hotel findByName(String name);

    public Hotel findByDescription(String description);

    public Hotel findByPrice(String price);

    @SuppressWarnings("null")
    public Optional<Hotel> findById(Long id);


   
}
