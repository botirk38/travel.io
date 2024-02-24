package com.travel.io.itinerary_service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel("test hotel", "This is a test hotel", "100", "testImageUrl", 5.0);
        hotel.setId(1L);
    }

    @Test
    void testGetName() {
        assertEquals("test hotel", hotel.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("This is a test hotel", hotel.getDescription());
    }

    @Test
    void testGetPrice() {
        assertEquals("100", hotel.getPrice());
    }

   

    @Test
    void testSetName() {
        hotel.setName("New Test Hotel");
        assertEquals("New Test Hotel", hotel.getName());
    }

    @Test
    void testSetDescription() {
        hotel.setDescription("This is a new test hotel");
        assertEquals("This is a new test hotel", hotel.getDescription());
    }

    @Test
    void testSetPrice() {
        hotel.setPrice("200");
        assertEquals("200", hotel.getPrice());
    }

    
    @Test
    void testEquals() {
        Hotel hotel2 = new Hotel("test hotel", "This is a test hotel", "100", "testImageUrl", 5.0);
        hotel2.setId(1L);
        assertEquals(hotel.hashCode(), hotel2.hashCode());
    }

    @Test
    void testHashCode() {
        Hotel hotel2 = new Hotel("test hotel", "This is a test hotel", "100", "testImageUrl", 5.0);
        hotel2.setId(1L);
        assertEquals(hotel.hashCode(), hotel2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Hotel{id=1, name='test hotel', description='This is a test hotel', price='100', imageUrl='testImageUrl'}";
        assertEquals(expected, hotel.toString());
    }
}