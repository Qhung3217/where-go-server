package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.restaurant.DetailRestaurantDto;
import com.wherego.wheregoserver.dto.restaurant.RestaurantFiterInforDto;
import com.wherego.wheregoserver.dto.restaurant.SimpleRestaurantDto;
import com.wherego.wheregoserver.mapper.RestaurantMapper;
import com.wherego.wheregoserver.repository.RestaurantRepository;
import com.wherego.wheregoserver.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
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

    @Override
    public List<SimpleRestaurantDto> getRandom(Integer quantity) {
        return restaurantRepository.getRandom(quantity)
                .stream()
                .map(restaurantMapper::toSimpleRestaurantDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimpleRestaurantDto> search(String key) {
        return restaurantRepository.search(key)
                .stream()
                .map(restaurantMapper::toSimpleRestaurantDto)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantFiterInforDto getFilterInfor() {
        return new RestaurantFiterInforDto(restaurantRepository.getCuisines(),
                restaurantRepository.getFeatures(), restaurantRepository.getMeals());
    }

    @Override
    public DetailRestaurantDto getById(Long id) {
        return restaurantMapper.toDetailRestaurantDto(restaurantRepository.getById(id));
    }
}
