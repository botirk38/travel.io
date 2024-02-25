package com.travel.io.itinerary_service.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String likes;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    private Itinerary itinerary;

    @OneToMany
    private List<DayPlan> dayPlans;

    @OneToMany
    private List<LocalWonder> localWonders;

    @OneToMany
    private List<Hotel> hotels;

    public Destination() {
    }

    public Destination(String name, String description, String likes, List<DayPlan> dayPlans,
            List<LocalWonder> localWonders, List<Hotel> hotels, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.likes = likes;
        this.dayPlans = dayPlans;
        this.localWonders = localWonders;
        this.hotels = hotels;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLikes() {
        return likes;
    }

    public List<DayPlan> getDayPlans() {
        return dayPlans;
    }

    public List<LocalWonder> getLocalWonders() {
        return localWonders;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public void setDayPlans(List<DayPlan> dayPlans) {
        this.dayPlans = dayPlans;
    }

    public void setLocalWonders(List<LocalWonder> localWonders) {
        this.localWonders = localWonders;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Destination destination = (Destination) o;
        return id.equals(destination.id);
    }

    public int hashCode() {
        return id.hashCode();

    }

    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", likes='" + likes + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dayPlans=" + dayPlans +
                ", localWonders=" + localWonders +
                ", hotels=" + hotels +
                '}';
    }


}
