package com.travel.io.itinerary_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.travel.io.itinerary_service.model.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long>{

    public Itinerary findByName(String name);


    @SuppressWarnings("null")
    public Optional<Itinerary> findById(Long id);
    
}
