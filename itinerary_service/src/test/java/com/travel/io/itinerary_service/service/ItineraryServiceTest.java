package com.travel.io.itinerary_service.service;

import com.travel.io.itinerary_service.dto.TravelPlanDto;
import com.travel.io.itinerary_service.dto.TravelPlanDto.ActivityDto;
import com.travel.io.itinerary_service.dto.TravelPlanDto.DestinationDto;
import com.travel.io.itinerary_service.dto.TravelPlanDto.PlanDto;
import com.travel.io.itinerary_service.model.*;
import com.travel.io.itinerary_service.repository.ItineraryRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItineraryServiceTest {

    @InjectMocks
    ItineraryService itineraryService;

    @Mock
    ItineraryRepository itineraryRepository;

    @Mock
    SerpAPIService googleImagesAPIService;

    

    @SuppressWarnings("null")
    @Test
    void testCreateItinerary() {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setName("Test Plan");
        travelPlanDto.setDestinations(Arrays.asList(new DestinationDto()));

        when(itineraryRepository.save(any())).thenReturn(new Itinerary());

        Itinerary itinerary = itineraryService.createItinerary(travelPlanDto);

        assertNotNull(itinerary);
        verify(itineraryRepository, times(1)).save(any());
    }

    @Test
    void testCreateDestination() throws Exception {
        DestinationDto dtoDestination = new DestinationDto();
        dtoDestination.setName("Test Destination");
        dtoDestination.setDescription("This is a test destination");
        dtoDestination.setStartDate("2022-01-01");
        dtoDestination.setEndDate("2022-01-02");
        dtoDestination.setPlan(Arrays.asList(new PlanDto()));
        dtoDestination.setLocalWonders(Arrays.asList("Wonder1", "Wonder2"));
        dtoDestination.setHotels(Arrays.asList("Hotel1", "Hotel2"));

        when(googleImagesAPIService.fetchHotelDetails(anyString(), anyString(), anyString())).thenReturn(new Hotel());
        when(googleImagesAPIService.fetchLocalWonderImage(anyString())).thenReturn("http://example.com/image.jpg");

        Destination destination = itineraryService.createDestination(dtoDestination);

        assertNotNull(destination);
        assertEquals("Test Destination", destination.getName());
        assertEquals(LocalDate.parse("2022-01-01"), destination.getStartDate());
        assertEquals(LocalDate.parse("2022-01-02"), destination.getEndDate());
        assertFalse(destination.getDayPlans().isEmpty());
        assertFalse(destination.getLocalWonders().isEmpty());
        assertFalse(destination.getHotels().isEmpty());
    }

    @Test
    void testFetchHotels() throws Exception {
        when(googleImagesAPIService.fetchHotelDetails(anyString(), anyString(), anyString())).thenReturn(new Hotel());

        List<Hotel> hotels = itineraryService.fetchHotels(Arrays.asList("Hotel1", "Hotel2"), "2022-01-01",
                "2022-01-02");

        assertNotNull(hotels);
        assertEquals(2, hotels.size());
    }

    @Test
    void testFetchLocalWonders() {
        when(googleImagesAPIService.fetchLocalWonderImage(anyString())).thenReturn("http://example.com/image.jpg");

        List<LocalWonder> localWonders = itineraryService.fetchLocalWonders(Arrays.asList("Wonder1", "Wonder2"));

        assertNotNull(localWonders);
        assertEquals(2, localWonders.size());
    }

    @Test
    void testCreateDayPlans() {
        PlanDto dtoPlan = new PlanDto();
        dtoPlan.setActivities(Arrays.asList(new ActivityDto()));

        List<DayPlan> dayPlans = itineraryService.createDayPlans(Arrays.asList(dtoPlan));

        assertNotNull(dayPlans);
        assertEquals(1, dayPlans.size());
    }

    @Test
    void testCreateActivities() {
        ActivityDto dtoActivity = new ActivityDto();
        dtoActivity.setActivityName("Test Activity");
        dtoActivity.setDescription("This is a test activity");
        dtoActivity.setStartTime("10:00");
        dtoActivity.setEndTime("12:00");

        List<Activity> activities = itineraryService.createActivities(Arrays.asList(dtoActivity));

        assertNotNull(activities);
        assertEquals(1, activities.size());
        assertEquals("Test Activity", activities.get(0).getName());
        assertEquals("This is a test activity", activities.get(0).getDescription());
        assertEquals(LocalTime.parse("10:00"), activities.get(0).getStartTime());
        assertEquals(LocalTime.parse("12:00"), activities.get(0).getEndTime());
    }
}