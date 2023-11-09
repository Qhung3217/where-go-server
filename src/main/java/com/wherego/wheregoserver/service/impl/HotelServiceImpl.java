package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.hotel.HotelFilterInforDto;
import com.wherego.wheregoserver.respository.HotelRepository;
import com.wherego.wheregoserver.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public HotelFilterInforDto getHotelFilterInfor() {
        return new HotelFilterInforDto(hotelRepository.getRoomTypes(),
                hotelRepository.getRoomFeatures(),
                hotelRepository.getPropertyAmenities());
    }
}
