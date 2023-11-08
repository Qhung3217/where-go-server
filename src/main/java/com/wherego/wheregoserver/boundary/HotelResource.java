package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.HotelFilterInforDto;
import com.wherego.wheregoserver.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/hotel")
public class HotelResource {
    @Autowired
    private HotelService hotelService;
    @GetMapping(value = "/filter-infor", produces = "application/json")
    public ResponseEntity<HotelFilterInforDto> getHotelFilterInfor(){
        return new ResponseEntity<HotelFilterInforDto>(hotelService.getHotelFilterInfor(),
        HttpStatus.OK);
    }
}
