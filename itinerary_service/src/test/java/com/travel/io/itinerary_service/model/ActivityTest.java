
package com.travel.io.itinerary_service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    private Activity activity;

    @BeforeEach
    void setUp() {
        activity = new Activity("Test Activity", "This is a test activity", LocalTime.of(10, 0), LocalTime.of(12, 0));
        activity.setId(1L);
    }

    @Test
    void testGetName() {
        assertEquals("Test Activity", activity.getName());
    }

    @Test
    void testGetNameNull() {
        Activity activity = new Activity();
        assertNull(activity.getName());
    }



    @Test
    void testGetDescription() {
        assertEquals("This is a test activity", activity.getDescription());
    }

    @Test
    void testGetDescriptionNull() {
        Activity activity = new Activity();
        assertNull(activity.getDescription());
    }


    @Test
    void testGetStartTime() {
        assertEquals(LocalTime.of(10, 0), activity.getStartTime());
    }


    @Test
    void testGetEndTime() {
        assertEquals(LocalTime.of(12, 0), activity.getEndTime());
    }

    @Test
    void testSetName() {
        activity.setName("New Test Activity");
        assertEquals("New Test Activity", activity.getName());
    }

    @Test
    void testSetDescription() {
        activity.setDescription("This is a new test activity");
        assertEquals("This is a new test activity", activity.getDescription());
    }

    @Test
    void testSetStartTime() {
        activity.setStartTime(LocalTime.of(9, 0));
        assertEquals(LocalTime.of(9, 0), activity.getStartTime());
    }

    @Test
    void testSetEndTime() {
        activity.setEndTime(LocalTime.of(11, 0));
        assertEquals(LocalTime.of(11, 0), activity.getEndTime());
    }

    @Test
    void testEquals() {
        Activity activity2 = new Activity("Test Activity", "This is a test activity", LocalTime.of(10, 0), LocalTime.of(12, 0));
        activity2.setId(1L);
        assertEquals(activity.hashCode(), activity2.hashCode());

    }

    @Test
    void testHashCode() {
        Activity activity2 = new Activity("Test Activity", "This is a test activity", LocalTime.of(10, 0), LocalTime.of(12, 0));
        activity2.setId(1L);
        assertEquals(activity.hashCode(), activity2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Activity{id=1, name='Test Activity', description='This is a test activity', startTime=10:00, endTime=12:00}";
        assertEquals(expected, activity.toString());
    }
}