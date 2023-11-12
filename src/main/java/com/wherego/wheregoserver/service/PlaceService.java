package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.place.PlaceFilterInforDto;
import com.wherego.wheregoserver.dto.place.SimplePlaceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceService {
    List<SimplePlaceDto> getAll();
    PlaceFilterInforDto getFilterInfor();
    List<SimplePlaceDto> getRandom(Integer quantity);
    List<SimplePlaceDto> search(String keyword);
}
