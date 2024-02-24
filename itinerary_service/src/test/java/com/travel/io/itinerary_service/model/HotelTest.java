package com.travel.io.itinerary_service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel("Test Hotel", "This is a test hotel", "100", "50", "Test Address", "1234567890",
                "test@test.com", "www.test.com", "Test Image URL");
    }

    @Test
    void testGetName() {
        assertEquals("Test Hotel", hotel.getName());
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
        Hotel hotel2 = new Hotel("Test Hotel", "This is a test hotel", "100", "50", "Test Address", "1234567890",
                "test@test.com", "www.test.com", "Test Image URL");
        assertEquals(hotel.hashCode(), hotel2.hashCode());
    }

    @Test
    void testHashCode() {
        Hotel hotel2 = new Hotel("Test Hotel", "This is a test hotel", "100", "50", "Test Address", "1234567890",
                "test@test.com", "www.test.com", "Test Image URL");
        assertEquals(hotel.hashCode(), hotel2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Hotel{id=1, name='Test Hotel', description='This is a test hotel', price='100', likes='50', address='Test Address', phone='1234567890', email='test@test.com', website='www.test.com'}";
        assertEquals(expected, hotel.toString());
    }
}