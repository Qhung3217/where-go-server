package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.hotel.SimpleHotelDto;
import com.wherego.wheregoserver.dto.place.SimplePlaceDto;
import com.wherego.wheregoserver.dto.restaurant.SimpleRestaurantDto;
import com.wherego.wheregoserver.exception.MissingParamsException;
import com.wherego.wheregoserver.exception.ResourceInvalidException;
import com.wherego.wheregoserver.service.HotelService;
import com.wherego.wheregoserver.service.PlaceService;
import com.wherego.wheregoserver.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchResource {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PlaceService placeService;


    @GetMapping
    public <T>T search(@RequestParam(value = "category", required = false) String category,
                       @RequestParam(value = "keyword", required = false) String keyword) {

        if (category == null && keyword == null) {
            String[] strings = new String[]{"category", "keyword"};
            throw new MissingParamsException(strings);
        } else if (category == null) {
            String[] strings = new String[]{"category"};
            throw new MissingParamsException(strings);
        } else if (keyword == null) {
            String[] strings = new String[]{"keyword"};
            throw new MissingParamsException(strings);
        }

        switch (category) {
            case "hotel":
                return (T) searchHotel(keyword);
            case "restaurant":
                return (T) searchRestaurant(keyword);
            case "place":
                return (T) searchPlace(keyword);
            default: throw new ResourceInvalidException("category",category);
        }
    }

    private ResponseEntity<List<SimpleHotelDto>> searchHotel(String keyword) {
        return new ResponseEntity<List<SimpleHotelDto>>(hotelService.search(keyword),
                HttpStatus.OK);
    }

    private ResponseEntity<List<SimpleRestaurantDto>> searchRestaurant(String keyword){
        return new ResponseEntity<List<SimpleRestaurantDto>>(restaurantService.search(keyword),
                HttpStatus.OK);
    }

    private ResponseEntity<List<SimplePlaceDto>> searchPlace(String keyword){
        return new ResponseEntity<List<SimplePlaceDto>>(placeService.search(keyword),
                HttpStatus.OK);
    }

}
