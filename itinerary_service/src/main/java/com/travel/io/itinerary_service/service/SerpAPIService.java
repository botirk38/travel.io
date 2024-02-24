package com.travel.io.itinerary_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hw.serpapi.GoogleSearch;
import com.hw.serpapi.SerpApiSearchException;
import com.travel.io.itinerary_service.model.Hotel;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class SerpAPIService {

    @Value("${serp.api.key}")
    private String apiKey;

    public String fetchLocalWonderImage(String query) {

        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("engine", "google_images");
        params.put("api_key", apiKey);

        String imageUrl = "";

        GoogleSearch googleSearch = new GoogleSearch(params);

        try {
            JsonObject response = googleSearch.getJson();
            JsonArray imageResults = response.getAsJsonArray("images_results");

            JsonObject firstImage = imageResults.get(0).getAsJsonObject();
            imageUrl = firstImage.get("original").getAsString();

        } catch (SerpApiSearchException exception) {
            System.out.println(exception);
        }

        return imageUrl;

    }

    private JsonObject getFirstJsonObject(JsonArray jsonArray) {
        return jsonArray.size() > 0 ? jsonArray.get(0).getAsJsonObject() : null;
    }

    private String getStringProperty(JsonObject jsonObject, String property) {
        return jsonObject.has(property) ? jsonObject.get(property).getAsString() : null;
    }

    public List<Hotel> fetchHotelDetails(String query, String checkInDate, String checkoutDate) {

        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("engine", "google_hotels");
        params.put("api_key", apiKey);
        params.put("check_in", checkInDate);
        params.put("check_out", checkoutDate);

        GoogleSearch googleSearch = new GoogleSearch(params);
        List<Hotel> hotels = new ArrayList<>();

        try {
            JsonObject results = googleSearch.getJson();

            Optional.ofNullable(results.getAsJsonArray("properties"))
                    .ifPresent(properties -> {
                        for (JsonElement propertyElement : properties) {
                            Hotel hotel = new Hotel();

                            JsonObject property = propertyElement.getAsJsonObject();

                            Optional.ofNullable(getStringProperty(property, "name"))
                                    .ifPresent(hotel::setName);
                            Optional.ofNullable(getStringProperty(property, "description"))
                                    .ifPresent(hotel::setDescription);

                            Optional.ofNullable(property.getAsJsonArray("prices"))
                                    .map(this::getFirstJsonObject)
                                    .filter(firstPrice -> firstPrice.has("rate_per_night"))
                                    .map(firstPrice -> firstPrice.getAsJsonObject("rate_per_night"))
                                    .map(ratePerNight -> getStringProperty(ratePerNight, "lowest"))
                                    .ifPresent(hotel::setPrice);

                            Optional.ofNullable(property.getAsJsonArray("images"))
                                    .map(this::getFirstJsonObject)
                                    .map(firstImage -> getStringProperty(firstImage, "url"))
                                    .ifPresent(hotel::setImageUrl);

                            Optional.ofNullable(getStringProperty(property, "overall_rating"))
                                    .ifPresent(hotel::setRating);

                        }
                    });

        } catch (SerpApiSearchException e) {
            // handle exception
        }

        return hotels;
    }
}
