package com.travel.io.itinerary_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hw.serpapi.GoogleSearch;

@Configuration
public class WebConfig {

    @Bean
    GoogleSearch googleSearch() {
        return new GoogleSearch();
    }

}
