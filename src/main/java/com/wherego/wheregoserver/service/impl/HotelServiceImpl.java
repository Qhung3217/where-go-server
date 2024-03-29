package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.hotel.DetailHotelDto;
import com.wherego.wheregoserver.dto.hotel.HotelFilterInforDto;
import com.wherego.wheregoserver.dto.hotel.SimpleHotelDto;
import com.wherego.wheregoserver.mapper.HotelMapper;
import com.wherego.wheregoserver.repository.HotelRepository;
import com.wherego.wheregoserver.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;
    @Override
    public HotelFilterInforDto getHotelFilterInfor() {
        return new HotelFilterInforDto(hotelRepository.getRoomTypes(),
                hotelRepository.getRoomFeatures(),
                hotelRepository.getPropertyAmenities());
    }

    @Override
    public List<SimpleHotelDto> getAll() {
        return hotelRepository.getAll()
                .stream()
                .map(hotelMapper::toSimpleHotelDto)
                .collect(Collectors.toList());
        /*hotelMapper::toSimpleHotelDto
         -> lambda expression alternative*/
    }

    @Override
    public List<SimpleHotelDto> search(String key) {
        return hotelRepository.search(key)
                .stream()
                .map(hotel->hotelMapper.toSimpleHotelDto(hotel))
                .collect(Collectors.toList());
    }

    @Override
    public List<SimpleHotelDto> getRandom(Integer quantity) {
        return hotelRepository.getRandom(quantity)
                .stream()
                .map(hotelMapper::toSimpleHotelDto)
                .collect(Collectors.toList());
    }

    @Override
    public DetailHotelDto getById(Long id) {
        return hotelMapper.toDetailHotelDto((hotelRepository.getById(id)));
    }
}
