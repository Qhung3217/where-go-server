package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.place.SimplePlaceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceService {
    List<SimplePlaceDto> getAll();
}
