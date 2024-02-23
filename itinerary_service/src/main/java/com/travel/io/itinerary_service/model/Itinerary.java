package com.travel.io.itinerary_service.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String name;

    @OneToMany
    private List<Destination> destinations;

    public Itinerary() {
    }

    public Itinerary(Long userId, String name, List<Destination> destinations) {
        this.userId = userId;
        this.name = name;
        this.destinations = destinations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Itinerary itinerary = (Itinerary) o;
        return id.equals(itinerary.id);
    }


    public int hashCode() {
        return id.hashCode();
    }

    public String toString() {
        return "Itinerary{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", destinations=" + destinations +
                '}';
    }

}
