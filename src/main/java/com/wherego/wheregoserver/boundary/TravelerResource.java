package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.traveler.TravelerDto;
import com.wherego.wheregoserver.dto.writer.WriterDto;
import com.wherego.wheregoserver.service.TravelerService;
import com.wherego.wheregoserver.utils.HeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traveler")
public class TravelerResource {
    @Autowired
    private TravelerService travelerService;
    @GetMapping("/ping")
    public String ping(){
        return "ping";
    }

    @GetMapping(value="/detail")
    public ResponseEntity<TravelerDto> getDetail(@RequestHeader("Authorization") String authorizationHeader){
        String token = HeaderUtils.getToken(authorizationHeader);
        return ResponseEntity.ok(travelerService.getDetail(token));
    }
}
