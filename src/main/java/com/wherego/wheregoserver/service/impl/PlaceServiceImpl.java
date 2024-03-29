package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.place.DetailPlaceDto;
import com.wherego.wheregoserver.dto.place.PlaceFilterInforDto;
import com.wherego.wheregoserver.dto.place.SimplePlaceDto;
import com.wherego.wheregoserver.mapper.PlaceMapper;
import com.wherego.wheregoserver.repository.PlaceRepository;
import com.wherego.wheregoserver.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private PlaceMapper placeMapper;

    @Override
    public List<SimplePlaceDto> getAll() {
        return placeRepository.getAll()
                .stream()
                .map(placeMapper::toSimplePlaceDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlaceFilterInforDto getFilterInfor() {
        return new PlaceFilterInforDto(placeRepository.getPlaceTypes());
    }

    @Override
    public List<SimplePlaceDto> getRandom(Integer quantity) {
        return placeRepository.getRandom(quantity)
                .stream()
                .map(placeMapper::toSimplePlaceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplePlaceDto> search(String keyword) {
        return placeRepository.search(keyword)
                .stream()
                .map(placeMapper::toSimplePlaceDto)
                .collect(Collectors.toList());
    }

    @Override
    public DetailPlaceDto getById(Long id) {
        return placeMapper.toDetailPlaceDto(placeRepository.getById(id));
    }


}
