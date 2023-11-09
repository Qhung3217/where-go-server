package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.hotel.HotelFilterInforDto;
import org.springframework.stereotype.Service;

@Service
public interface HotelService {
    HotelFilterInforDto getHotelFilterInfor();

}
