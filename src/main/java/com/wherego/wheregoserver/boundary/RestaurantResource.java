package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.restaurant.RestaurantFiterInforDto;
import com.wherego.wheregoserver.dto.restaurant.SimpleRestaurantDto;
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
@RequestMapping(value = "/restaurant")
public class RestaurantResource {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<SimpleRestaurantDto>> getAll() {
        return new ResponseEntity<List<SimpleRestaurantDto>>(restaurantService.getAll(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/random")
    public ResponseEntity<List<SimpleRestaurantDto>> getRandom(@RequestParam(name = "quantity",
            required = false) Integer quantity) {
        return new ResponseEntity<List<SimpleRestaurantDto>>(restaurantService.getRandom(quantity), HttpStatus.OK);
    }

    @GetMapping(value="/filter-infor")
    public ResponseEntity<RestaurantFiterInforDto> getFilterInfor(){
        return new ResponseEntity<RestaurantFiterInforDto>(restaurantService.getFilterInfor(), HttpStatus.OK);
    }
}
