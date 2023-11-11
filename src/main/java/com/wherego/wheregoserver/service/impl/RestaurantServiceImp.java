package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.restaurant.SimpleRestaurantDto;
import com.wherego.wheregoserver.mapper.RestaurantMapper;
import com.wherego.wheregoserver.repository.RestaurantRepository;
import com.wherego.wheregoserver.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImp implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantMapper restaurantMapper;

    @Override
    public List<SimpleRestaurantDto> getAll() {
        return restaurantRepository.getAll()
                .stream()
                .map(restaurantMapper::toSimpleRestaurantDto)
                .collect(Collectors.toList());
    }
}
