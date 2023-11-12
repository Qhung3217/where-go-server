package com.wherego.wheregoserver.service.impl;

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
public class PlaceServiceImp implements PlaceService {
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


}
