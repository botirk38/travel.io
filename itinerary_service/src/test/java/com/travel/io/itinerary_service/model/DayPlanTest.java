package com.travel.io.itinerary_service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

class DayPlanTest {
    private DayPlan dayPlan;
    private Activity activity1;
    private Activity activity2;
    private List<Activity> activities;
    private Destination destination;

    @BeforeEach
    void setUp() {
        activity1 = new Activity();
        activity2 = new Activity();
        activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        destination = new Destination();
        dayPlan = new DayPlan(activities);
        dayPlan.setId(1L);
        dayPlan.setDestination(destination);
    }

    @Test
    void testGetId() {
        assertEquals(dayPlan.getId(), 1);
    }

    @Test
    void testGetActivities() {
        assertEquals(activities, dayPlan.getActivities());
    }

    @Test
    void testSetActivities() {
        Activity activity3 = new Activity();
        activities.add(activity3);
        dayPlan.setActivities(activities);
        assertEquals(activities, dayPlan.getActivities());
    }

    @Test
    void testGetDestination() {
        assertEquals(destination, dayPlan.getDestination());
    }

    @Test
    void testEquals() {
        DayPlan dayPlan2 = new DayPlan(activities);
        dayPlan2.setDestination(destination);
        dayPlan2.setId(1L);
        assertEquals(dayPlan, dayPlan2);
    }

    @Test
    void testHashCode() {
        DayPlan dayPlan2 = new DayPlan(activities);
        dayPlan2.setDestination(destination);
        dayPlan2.setId(1L);
        assertEquals(dayPlan.hashCode(), dayPlan2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "DayPlan{id=1, activities=" + activities + "}";
        assertEquals(expected, dayPlan.toString());
    }
}
