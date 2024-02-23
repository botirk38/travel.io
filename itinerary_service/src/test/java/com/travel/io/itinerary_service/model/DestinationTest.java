package com.travel.io.itinerary_service.model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DestinationTest {

    private Destination destination;

    @BeforeEach
    void setUp() {
        List<DayPlan> dayPlans = Arrays.asList(new DayPlan(), new DayPlan());
        List<LocalWonder> localWonders = Arrays.asList(new LocalWonder(), new LocalWonder());
        List<Hotel> hotels = Arrays.asList(new Hotel(), new Hotel());

        destination = new Destination("Test Destination", "This is a test destination", "1000", dayPlans, localWonders, hotels, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10));
        destination.setId(1L);
    }

    @Test
    void testGetName() {
        assertEquals("Test Destination", destination.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("This is a test destination", destination.getDescription());
    }

    @Test
    void testGetLikes() {
        assertEquals("1000", destination.getLikes());
    }

    @Test
    void testGetDayPlans() {
        assertEquals(2, destination.getDayPlans().size());
    }

    @Test
    void testGetLocalWonders() {
        assertEquals(2, destination.getLocalWonders().size());
    }

    @Test
    void testGetHotels() {
        assertEquals(2, destination.getHotels().size());
    }

    @Test
    void testGetStartDate() {
        assertEquals(LocalDate.of(2022, 1, 1), destination.getStartDate());
    }

    @Test
    void testGetEndDate() {
        assertEquals(LocalDate.of(2022, 1, 10), destination.getEndDate());
    }

    @Test
    void testSetName() {
        destination.setName("New Test Destination");
        assertEquals("New Test Destination", destination.getName());
    }

    @Test
    void testSetDescription() {
        destination.setDescription("This is a new test destination");
        assertEquals("This is a new test destination", destination.getDescription());
    }

    @Test
    void testSetLikes() {
        destination.setLikes("2000");
        assertEquals("2000", destination.getLikes());
    }

    @Test
    void testEquals() {
        Destination destination2 = new Destination("Test Destination", "This is a test destination", "1000", null, null, null, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10));
        destination2.setId(1L);
        assertEquals(destination, destination2);
    }

    @Test
    void testHashCode() {
        Destination destination2 = new Destination("Test Destination", "This is a test destination", "1000", null, null, null, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10));
        destination2.setId(1L);
        assertEquals(destination.hashCode(), destination2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Destination{id=1, name='Test Destination', description='This is a test destination', likes='1000', startDate=2022-01-01, endDate=2022-01-10, dayPlans=[DayPlan{id=null, activities=null}, DayPlan{id=null, activities=null}], localWonders=[LocalWonder [destination=null, id=null, imageUrl=null, name=null], LocalWonder [destination=null, id=null, imageUrl=null, name=null]], hotels=[Hotel{id=null, name='null', description='null', price='null', likes='null', address='null', phone='null', email='null', website='null'}, Hotel{id=null, name='null', description='null', price='null', likes='null', address='null', phone='null', email='null', website='null'}]}";
        assertEquals(expected, destination.toString());
    }
}