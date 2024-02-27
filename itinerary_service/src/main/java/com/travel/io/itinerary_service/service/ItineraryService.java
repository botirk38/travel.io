package com.travel.io.itinerary_service.service;

import org.springframework.dao.DataAccessException;
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
import java.time.LocalDate;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;

    private final SerpAPIService googleImagesAPIService;

    public ItineraryService(ItineraryRepository itineraryRepository, SerpAPIService googleImagesAPIService) {
        this.itineraryRepository = itineraryRepository;
        this.googleImagesAPIService = googleImagesAPIService;
    }

    public Itinerary createItinerary(TravelPlanDto travelPlanDto) {
        Itinerary itinerary = new Itinerary();

        try {

            itinerary.setName(travelPlanDto.getName());

            for (DestinationDto dtoDestination : travelPlanDto.getDestinations()) {
                Destination destination = createDestination(dtoDestination);
                destination.setItinerary(itinerary);
                itinerary.getDestinations().add(destination);

            }

        } catch (Exception e) {
            throw new DataAccessException("Error while creating itinerary", e) {
            };
        }

        return itineraryRepository.save(itinerary);

    }

    public Itinerary fetchItinerary(Long id) {
        return itineraryRepository.findById(id).orElse(null);
    }

    public void deleteItinerary(Long id) {
        Itinerary itinerary = itineraryRepository.findById(id).orElse(null);
        if (itinerary != null) {
            itineraryRepository.delete(itinerary);
        }
    }

    public void updateItinerary(Long id, Itinerary itinerary) {
        itinerary.setId(id);
        itineraryRepository.save(itinerary);
    }

    Destination createDestination(DestinationDto dtoDestination) {
        Destination destination = new Destination();

        try {

            destination.setName(dtoDestination.getName());
            destination.setDescription(dtoDestination.getDescription());
            destination.setStartDate(LocalDate.parse(dtoDestination.getStartDate()));
            destination.setEndDate(LocalDate.parse(dtoDestination.getEndDate()));
            destination.setDayPlans(createDayPlans(dtoDestination.getPlan()));
            destination.setLocalWonders(fetchLocalWonders(dtoDestination.getLocalWonders()));
            destination.setHotels(
                    fetchHotels(dtoDestination.getHotels(), dtoDestination.getStartDate(),
                            dtoDestination.getEndDate()));
        } catch (Exception e) {
            throw new DataAccessException("Error while creating destination", e) {
            };
        }
        return destination;
    }

    List<Hotel> fetchHotels(List<String> hotels, String startDate, String endDate) {

        List<Hotel> hotelList = new ArrayList<>();

        try {
            for (String hotel : hotels) {
                Hotel hotelObj = googleImagesAPIService.fetchHotelDetails(hotel, startDate, endDate);
                hotelList.add(hotelObj);
            }

        } catch (Exception e) {
            throw new DataAccessException("Error while fetching hotels", e) {
            };
        }

        return hotelList;
    }

    List<LocalWonder> fetchLocalWonders(List<String> localWonders) {
        List<LocalWonder> localWonderList = new ArrayList<>();

        try {
            for (String localWonder : localWonders) {
                LocalWonder wonder = new LocalWonder();
                wonder.setName(localWonder);
                String image = googleImagesAPIService.fetchLocalWonderImage(localWonder);
                wonder.setImageUrl(image);
                localWonderList.add(wonder);
            }
        } catch (Exception e) {

            throw new DataAccessException("Error while fetching local wonders", e) {
            };
        }
        return localWonderList;
    }

    List<DayPlan> createDayPlans(List<PlanDto> plan) {

        if (plan == null) {
            return new ArrayList<>();
        }

        List<DayPlan> dayPlans = new ArrayList<>();

        try {
            for (PlanDto dtoPlan : plan) {
                DayPlan dayPlan = new DayPlan();
                dayPlan.setActivities(createActivities(dtoPlan.getActivities()));
                dayPlans.add(dayPlan);
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw new DataAccessException("Error while creating day plans", e) {
            };

        }
        return dayPlans;

    }

    List<Activity> createActivities(List<ActivityDto> activities) {

        if (activities == null) {
            return new ArrayList<>();
        }

        List<Activity> activityList = new ArrayList<>();

        try {
            for (ActivityDto dtoActivity : activities) {

                Activity activity = new Activity();
                activity.setName(dtoActivity.getActivityName());
                activity.setDescription(dtoActivity.getDescription());
                activity.setStartTime(LocalTime.parse(dtoActivity.getStartTime()));
                activity.setEndTime(LocalTime.parse(dtoActivity.getEndTime()));
                activityList.add(activity);
            }
        } catch (Exception e) {

            throw new DataAccessException("Error while creating activities", e) {
            };

        }
        return activityList;
    }

}
