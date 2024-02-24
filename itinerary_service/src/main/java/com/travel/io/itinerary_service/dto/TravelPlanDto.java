package com.travel.io.itinerary_service.dto;
import java.util.List;

public class TravelPlanDto {
    
    public static class ActivityDto {
        private String activityName;
        private String description;
        private String startTime;
        private String endTime;



        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }


    }

    public static class PlanDto {
        private List<ActivityDto> activities;

        public List<ActivityDto> getActivities() {
            return activities;
        }

        public void setActivities(List<ActivityDto> activities) {
            this.activities = activities;
        }
    }

    public static class DestinationDto {
        private String name;
        private String description;
        private List<String> likes;
        private List<PlanDto> plan;
        private List<String> hotels;
        private List<String> localWonders;
        private String startDate;
        private String endDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getLikes() {
            return likes;
        }

        public void setLikes(List<String> likes) {
            this.likes = likes;
        }

        public List<PlanDto> getPlan() {
            return plan;
        }

        public void setPlan(List<PlanDto> plan) {
            this.plan = plan;
        }

        public List<String> getHotels() {
            return hotels;
        }

        public void setHotels(List<String> hotels) {
            this.hotels = hotels;
        }


        public List<String> getLocalWonders() {
            return localWonders;
        }

        public void setLocalWonders(List<String> localWonders) {
            this.localWonders = localWonders;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
        
    }

    private List<DestinationDto> destinations;
    private String name;


    public List<DestinationDto> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationDto> destinations) {
        this.destinations = destinations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
