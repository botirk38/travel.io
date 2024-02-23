package com.travel.io.itinerary_service.repository;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travel.io.itinerary_service.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long>{

    public Activity findByName(String name);

    public Activity findByDescription(String description);

    public Activity findByStartTime(LocalTime startTime);

    public Activity findByEndTime(LocalTime endTime);





    
    
}
