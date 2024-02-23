package com.travel.io.itinerary_service.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class ItineraryTest {

    @Test
    public void testConstructor() {
        List<Destination> destinations = Arrays.asList(new Destination(), new Destination());
        Itinerary itinerary = new Itinerary(1L, "Test Itinerary", destinations);

        assertEquals(1L, itinerary.getUserId());
        assertEquals("Test Itinerary", itinerary.getName());
        assertEquals(destinations, itinerary.getDestinations());
    }

    @Test
    public void testSettersAndGetters() {
        Itinerary itinerary = new Itinerary();
        itinerary.setId(1L);
        itinerary.setUserId(1L);
        itinerary.setName("Test Itinerary");

        List<Destination> destinations = Arrays.asList(new Destination(), new Destination());
        itinerary.setDestinations(destinations);

        assertEquals(1L, itinerary.getId());
        assertEquals(1L, itinerary.getUserId());
        assertEquals("Test Itinerary", itinerary.getName());
        assertEquals(destinations, itinerary.getDestinations());
    }

    @Test
    public void testEquals() {
        Itinerary itinerary1 = new Itinerary(1L, "Test Itinerary", Arrays.asList(new Destination(), new Destination()));
        Itinerary itinerary2 = new Itinerary(1L, "Test Itinerary", Arrays.asList(new Destination(), new Destination()));
        itinerary1.setId(1L);
        itinerary2.setId(1L);
        assertTrue(itinerary1.equals(itinerary2));
    }

    @Test
    public void testHashCode() {
        Itinerary itinerary = new Itinerary();
        itinerary.setId(1L);

        assertEquals(itinerary.getId().hashCode(), itinerary.hashCode());
    }

    @Test
    public void testToString() {
        List<Destination> destinations = Arrays.asList(new Destination(), new Destination());
        Itinerary itinerary = new Itinerary(1L, "Test Itinerary", destinations);

        String expected = "Itinerary{id=" + itinerary.getId() + ", userId=1, name='Test Itinerary', destinations=" + destinations + "}";
        assertEquals(expected, itinerary.toString());
    }
}
