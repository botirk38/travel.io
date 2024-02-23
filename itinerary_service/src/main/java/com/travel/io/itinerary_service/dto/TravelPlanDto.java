package com.travel.io.itinerary_service.dto;
import java.util.List;

public class TravelPlanDto {
    
    public static class Activity {
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

    public static class Plan {
        private List<Activity> activities;

        public List<Activity> getActivities() {
            return activities;
        }

        public void setActivities(List<Activity> activities) {
            this.activities = activities;
        }
    }

    public static class Destination {
        private String name;
        private String description;
        private List<String> likes;
        private Plan plan;
        private List<String> hotels;
        private List<String> localWonders;

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

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
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
        
    }

    private List<Destination> destinations;

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }


}
