package com.travel.io.itinerary_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travel.io.itinerary_service.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long>{

    public Destination findByName(String name);

    public Destination findByDescription(String description);

    
}
