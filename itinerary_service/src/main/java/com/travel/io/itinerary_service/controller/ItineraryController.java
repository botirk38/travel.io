package com.travel.io.itinerary_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.travel.io.itinerary_service.dto.TravelPlanDto;
import com.travel.io.itinerary_service.model.Itinerary;
import com.travel.io.itinerary_service.service.ItineraryService;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, ?>> createItinerary(@RequestBody TravelPlanDto travelPlanDto) {

        if (travelPlanDto == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid travel plan"));
        }

        if (travelPlanDto.getDestinations() == null || travelPlanDto.getDestinations().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid destinations"));
        }

        Itinerary itinerary = itineraryService.createItinerary(travelPlanDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itinerary.getId())
                .toUri();

        return ResponseEntity.created(location).body(Map.of("Itinerary:", itinerary));

    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<Map<String, ?>> fetchItinerary(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid id"));
        }

        Itinerary itinerary = itineraryService.fetchItinerary(id);

        return ResponseEntity.ok(Map.of("Itinerary:", itinerary));

    }

    @GetMapping("/fetch/all")
    public ResponseEntity<Map<String, ?>> fetchAllItineraries() {

        return ResponseEntity.ok(Map.of("Itineraries:", itineraryService.fetchAllItineraries()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, ?>> updateItinerary(@PathVariable Long id, @RequestBody Itinerary itinerary) {

        if (id == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid id"));
        }

        if (itinerary == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid itinerary"));
        }

        itineraryService.updateItinerary(id, itinerary);

        return ResponseEntity.ok(Map.of("Itinerary:", "Updated"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, ?>> deleteItinerary(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid id"));
        }

        itineraryService.deleteItinerary(id);

        return ResponseEntity.ok(Map.of("Itinerary:", "Deleted"));

    }

}
