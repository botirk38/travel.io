package com.travel.io.itinerary_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hw.serpapi.GoogleSearch;
import com.hw.serpapi.SerpApiSearchException;
import com.travel.io.itinerary_service.model.Hotel;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.JsonArray;
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

    public Hotel fetchHotelDetails(String query, String checkInDate, String checkoutDate) {

        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("engine", "google_hotels");
        params.put("api_key", apiKey);
        params.put("check_in", checkInDate);
        params.put("check_out", checkoutDate);

        Hotel hotel = new Hotel();

        GoogleSearch googleSearch = new GoogleSearch(params);

        try {
            JsonObject results = googleSearch.getJson();

            if (results.has("properties")) {
                JsonArray properties = results.getAsJsonArray("properties");

                if (properties.size() > 0) {
                    JsonObject firstHotel = properties.get(0).getAsJsonObject();

                    if (firstHotel.has("name")) {
                        String name = firstHotel.get("name").getAsString();
                        String description = firstHotel.get("description").getAsString();
                        hotel.setName(name);
                        hotel.setDescription(description);

                        if (firstHotel.has("prices")) {
                            JsonArray prices = firstHotel.getAsJsonArray("prices");
                            JsonObject firstPrice = prices.get(0).getAsJsonObject();

                            if (firstPrice.has("rate_per_night")) {
                                JsonObject ratePerNight = firstPrice.getAsJsonObject("rate_per_night");
                                String lowestPrice = ratePerNight.get("lowest").getAsString();
                                hotel.setPrice(lowestPrice);

                            }
                        }

                    }
                }
            }
        } catch (SerpApiSearchException exception) {
            System.out.println(exception);
        }

    }
}
