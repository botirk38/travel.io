package com.travel.io.itinerary_service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(1L, "Test Hotel", "This is a test hotel", "100", "50", "Test Address", "1234567890", "test@test.com", "www.test.com");
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
    void testGetLikes() {
        assertEquals("50", hotel.getLikes());
    }

    @Test
    void testGetAddress() {
        assertEquals("Test Address", hotel.getAddress());
    }

    @Test
    void testGetPhone() {
        assertEquals("1234567890", hotel.getPhone());
    }

    @Test
    void testGetEmail() {
        assertEquals("test@test.com", hotel.getEmail());
    }

    @Test
    void testGetWebsite() {
        assertEquals("www.test.com", hotel.getWebsite());
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
    void testSetLikes() {
        hotel.setLikes("100");
        assertEquals("100", hotel.getLikes());
    }

    @Test
    void testSetAddress() {
        hotel.setAddress("New Test Address");
        assertEquals("New Test Address", hotel.getAddress());
    }

    @Test
    void testSetPhone() {
        hotel.setPhone("0987654321");
        assertEquals("0987654321", hotel.getPhone());
    }

    @Test
    void testSetEmail() {
        hotel.setEmail("newtest@test.com");
        assertEquals("newtest@test.com", hotel.getEmail());
    }

    @Test
    void testSetWebsite() {
        hotel.setWebsite("www.newtest.com");
        assertEquals("www.newtest.com", hotel.getWebsite());
    }

    @Test
    void testEquals() {
        Hotel hotel2 = new Hotel(1L, "Test Hotel", "This is a test hotel", "100", "50", "Test Address", "1234567890", "test@test.com", "www.test.com");
        assertEquals(hotel.hashCode(), hotel2.hashCode());
    }

    @Test
    void testHashCode() {
        Hotel hotel2 = new Hotel(1L, "Test Hotel", "This is a test hotel", "100", "50", "Test Address", "1234567890", "test@test.com", "www.test.com");
        assertEquals(hotel.hashCode(), hotel2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Hotel{id=1, name='Test Hotel', description='This is a test hotel', price='100', likes='50', address='Test Address', phone='1234567890', email='test@test.com', website='www.test.com'}";
        assertEquals(expected, hotel.toString());
    }
}