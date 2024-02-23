package com.travel.io.itinerary_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.io.itinerary_service.model.DayPlan;

public interface DayPlanRepository extends JpaRepository<DayPlan, Long> {

    @SuppressWarnings("null")
    public Optional<DayPlan> findById(Long id);

    

}
