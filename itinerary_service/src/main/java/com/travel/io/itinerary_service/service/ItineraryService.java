package com.travel.io.itinerary_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.io.itinerary_service.dto.TravelPlanDto;
import com.travel.io.itinerary_service.dto.TravelPlanDto.ActivityDto;
import com.travel.io.itinerary_service.dto.TravelPlanDto.DestinationDto;
import com.travel.io.itinerary_service.dto.TravelPlanDto.PlanDto;
import com.travel.io.itinerary_service.model.Activity;
import com.travel.io.itinerary_service.model.DayPlan;
import com.travel.io.itinerary_service.model.Destination;
import com.travel.io.itinerary_service.model.Hotel;
import com.travel.io.itinerary_service.model.Itinerary;
import com.travel.io.itinerary_service.model.LocalWonder;
import com.travel.io.itinerary_service.repository.ItineraryRepository;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
@Service
public class ItineraryService {


    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private SerpAPIService googleImagesAPIService;


    public Itinerary createItinerary(TravelPlanDto travelPlanDto){

        Itinerary itinerary = new Itinerary();
        itinerary.setName(travelPlanDto.getName());

        for(DestinationDto dtoDestination : travelPlanDto.getDestinations()){
            Destination itineraryDestination = new Destination();
            itineraryDestination.setName(dtoDestination.getName());
            itineraryDestination.setDescription(dtoDestination.getDescription());
            
            List<DayPlan> dayPlans = new ArrayList<>();
            List<LocalWonder> localWonders = new ArrayList<>();

            for(PlanDto dtoPlan : dtoDestination.getPlan()){
                DayPlan dayPlan = new DayPlan();
                List<Activity> activities = new ArrayList<>();

                for(ActivityDto dtoActivity : dtoPlan.getActivities()){
                    Activity activity = new Activity();
                    activity.setName(dtoActivity.getActivityName());
                    activity.setDescription(dtoActivity.getDescription());
                    activity.setStartTime(LocalTime.parse(dtoActivity.getStartTime()));
                    activity.setEndTime(LocalTime.parse(dtoActivity.getEndTime()));
                    activities.add(activity);
                }


                dayPlan.setActivities(activities);
                dayPlans.add(dayPlan);
            }

            for(String localWonder : dtoDestination.getLocalWonders()){
                LocalWonder wonder = new LocalWonder();
                wonder.setName(localWonder);
                String image= googleImagesAPIService.fetchLocalWonderImage(localWonder);
                wonder.setImageUrl(image);
                localWonders.add(wonder);
            }

            for(String hotel: dtoDestination.getHotels()){
                Hotel hotelItinerary = new Hotel();
                hotelItinerary.setName(hotel);
                googleImagesAPIService.fetchHotelDetails(hotel, dtoDestination.getStartDate(), dtoDestination.getEndDate());
            }

            itinerary.getDestinations().add(itineraryDestination);

        }

        return itineraryRepository.save(itinerary);

    }
    


    
}
