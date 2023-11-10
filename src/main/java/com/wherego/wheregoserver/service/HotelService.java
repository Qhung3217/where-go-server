package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.hotel.DetailHotelDto;
import com.wherego.wheregoserver.dto.hotel.HotelFilterInforDto;
import com.wherego.wheregoserver.dto.hotel.SimpleHotelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    HotelFilterInforDto getHotelFilterInfor();
    List<SimpleHotelDto> getAll();
    List<SimpleHotelDto> search(String key);
    List<SimpleHotelDto> getRandom(int quantity);
    DetailHotelDto getById(Long id);
}
