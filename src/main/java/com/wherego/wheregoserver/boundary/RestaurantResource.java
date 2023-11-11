package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.restaurant.SimpleRestaurantDto;
import com.wherego.wheregoserver.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/restaurant")
public class RestaurantResource {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<SimpleRestaurantDto>> getAll(){
        return new ResponseEntity<List<SimpleRestaurantDto>>(restaurantService.getAll(),
                HttpStatus.OK);
    }
}
