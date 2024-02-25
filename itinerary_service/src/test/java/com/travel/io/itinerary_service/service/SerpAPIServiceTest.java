package com.travel.io.itinerary_service.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hw.serpapi.GoogleSearch;
import com.hw.serpapi.SerpApiSearchException;
import com.travel.io.itinerary_service.model.Hotel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SerpAPIServiceTest {

    @Mock
    private GoogleSearch googleSearch;

    @InjectMocks
    private SerpAPIService serpAPIService;

    @Test
    void testFetchHotelDetails() throws Exception {
        // Arrange
        String query = "test";
        String checkInDate = "2022-01-01";
        String checkoutDate = "2022-01-02";

        JsonObject mockResults = new JsonObject();
        JsonArray mockProperties = new JsonArray();
        JsonObject mockProperty = new JsonObject();
        mockProperty.addProperty("name", "Test Hotel");
        mockProperties.add(mockProperty);
        mockResults.add("properties", mockProperties);

        when(googleSearch.getJson()).thenReturn(mockResults);

        // Act

        Hotel hotel = serpAPIService.fetchHotelDetails(query, checkInDate, checkoutDate);

        // Assert

        assertNotNull(hotel);
        assertEquals("Test Hotel", hotel.getName());

    }

    @Test
    void testFetchHotelDetailsNoProperties() throws Exception {
        // Arrange
        String query = "test";
        String checkInDate = "2022-01-01";
        String checkoutDate = "2022-01-02";

        JsonObject mockResults = new JsonObject();

        when(googleSearch.getJson()).thenReturn(mockResults);

        // Act

        // Assert

        assertThrows(Exception.class, () -> serpAPIService.fetchHotelDetails(query, checkInDate, checkoutDate));


    }

    @Test
    void testFetchHotelDetailsException() {
        // Arrange
        String query = "test";
        String checkInDate = "2022-01-01";
        String checkoutDate = "2022-01-02";

        when(googleSearch.getJson()).thenThrow(new SerpApiSearchException("Test exception"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> serpAPIService.fetchHotelDetails(query, checkInDate, checkoutDate));
    }

}
