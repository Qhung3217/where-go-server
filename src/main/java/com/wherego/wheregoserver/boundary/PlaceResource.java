package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.place.PlaceFilterInforDto;
import com.wherego.wheregoserver.dto.place.SimplePlaceDto;
import com.wherego.wheregoserver.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/place")
public class PlaceResource {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<SimplePlaceDto>> getAll(){
        return new ResponseEntity<List<SimplePlaceDto>>(placeService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value="filter-infor")
    public ResponseEntity<PlaceFilterInforDto> getFilterInfor(){
        return new ResponseEntity<PlaceFilterInforDto>(placeService.getFilterInfor(), HttpStatus.OK);
    }
}
