package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.hotel.HotelFilterInforDto;
import com.wherego.wheregoserver.dto.hotel.SimpleHotelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    HotelFilterInforDto getHotelFilterInfor();
    List<SimpleHotelDto> getAll();
}
