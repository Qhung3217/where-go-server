package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.restaurant.RestaurantFiterInforDto;
import com.wherego.wheregoserver.dto.restaurant.SimpleRestaurantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    List<SimpleRestaurantDto> getAll();
    List<SimpleRestaurantDto> getRandom(Integer quantity);
    List<SimpleRestaurantDto> search(String key);
    RestaurantFiterInforDto getFilterInfor();
}
