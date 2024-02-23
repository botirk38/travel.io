package com.travel.io.itinerary_service.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DayPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Activity> activities;

    @ManyToOne
    private Destination destination;

    public DayPlan() {
    }

    public DayPlan(List<Activity> activities) {
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
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
        DayPlan dayPlan = (DayPlan) o;
        return id.equals(dayPlan.id);
    }

    public int hashCode() {
        return id.hashCode();
    }

    public String toString() {
        return "DayPlan{" +
                "id=" + id +
                ", activities=" + activities +
                '}';
    }

}
