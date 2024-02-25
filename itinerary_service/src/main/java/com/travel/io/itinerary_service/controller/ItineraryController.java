package com.travel.io.itinerary_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.travel.io.itinerary_service.dto.TravelPlanDto;
import com.travel.io.itinerary_service.model.Itinerary;
import com.travel.io.itinerary_service.service.ItineraryService;

import java.util.Map;

@Service
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    public ResponseEntity<Map<String, ?>> createItinerary(TravelPlanDto travelPlanDto) {

        if (travelPlanDto == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid travel plan"));
        }

        if (travelPlanDto.getDestinations() == null || travelPlanDto.getDestinations().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid destinations"));
        }

        Itinerary itinerary = itineraryService.createItinerary(travelPlanDto);

        return ResponseEntity.ok(Map.of("itinerary", itinerary));

    }

}
