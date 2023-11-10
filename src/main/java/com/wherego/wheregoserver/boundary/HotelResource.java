package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.hotel.HotelFilterInforDto;
import com.wherego.wheregoserver.dto.hotel.SimpleHotelDto;
import com.wherego.wheregoserver.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/hotel")
public class HotelResource {
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<SimpleHotelDto>> getAll(){
        return new ResponseEntity<List<SimpleHotelDto>>(hotelService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/filter-infor")
    public ResponseEntity<HotelFilterInforDto> getHotelFilterInfor(){
        return new ResponseEntity<HotelFilterInforDto>(hotelService.getHotelFilterInfor(),
        HttpStatus.OK);
    }



}
