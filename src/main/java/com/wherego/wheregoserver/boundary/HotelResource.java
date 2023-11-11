package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.hotel.DetailHotelDto;
import com.wherego.wheregoserver.dto.hotel.HotelFilterInforDto;
import com.wherego.wheregoserver.dto.hotel.SimpleHotelDto;
import com.wherego.wheregoserver.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/hotel")
public class HotelResource {
    @Autowired
    private HotelService hotelService;


    @GetMapping
    public ResponseEntity<List<SimpleHotelDto>> getAll() {
        return new ResponseEntity<List<SimpleHotelDto>>(hotelService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/filter-infor")
    public ResponseEntity<HotelFilterInforDto> getHotelFilterInfor() {
        return new ResponseEntity<HotelFilterInforDto>(hotelService.getHotelFilterInfor(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/random")
    public ResponseEntity<List<SimpleHotelDto>> getRandom(@RequestParam(value = "quantity",
            required = false) Integer quantity) {
        return new ResponseEntity<List<SimpleHotelDto>>(hotelService.getRandom(quantity),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DetailHotelDto> getById(@PathVariable Long id) {
        return new ResponseEntity<DetailHotelDto>(hotelService.getById(id), HttpStatus.OK);
    }

}
