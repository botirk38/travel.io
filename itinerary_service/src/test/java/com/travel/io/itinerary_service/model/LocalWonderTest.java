package com.travel.io.itinerary_service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LocalWonderTest {
    private LocalWonder localWonder;

    @BeforeEach
    public void setUp() {
        localWonder = new LocalWonder();
    }

    @Test
    public void testId() {
        Long idValue = 1L;
        localWonder.setId(idValue);
        assertEquals(idValue, localWonder.getId());
    }

    @Test
    public void testName() {
        String nameValue = "Test Name";
        localWonder.setName(nameValue);
        assertEquals(nameValue, localWonder.getName());
    }

    @Test
    public void testImageUrl() {
        String imageUrlValue = "Test Image URL";
        localWonder.setImageUrl(imageUrlValue);
        assertEquals(imageUrlValue, localWonder.getImageUrl());
    }

    @Test
    public void testDestination() {
        Destination destinationValue = new Destination();
        localWonder.setDestination(destinationValue);
        assertEquals(destinationValue, localWonder.getDestination());
    }

    @Test
    public void testEquals() {
        LocalWonder localWonder1 = new LocalWonder();
        localWonder1.setId(1L);
        LocalWonder localWonder2 = new LocalWonder();
        localWonder2.setId(1L);
        assertTrue(localWonder1.equals(localWonder2));
        assertTrue(localWonder2.equals(localWonder1));
    }

    @Test
    public void testNotEquals() {
        LocalWonder localWonder1 = new LocalWonder();
        localWonder1.setId(1L);
        LocalWonder localWonder2 = new LocalWonder();
        localWonder2.setId(2L);
        assertFalse(localWonder1.equals(localWonder2));
        assertFalse(localWonder2.equals(localWonder1));
    }
}