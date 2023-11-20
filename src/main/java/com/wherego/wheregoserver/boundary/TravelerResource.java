package com.wherego.wheregoserver.boundary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traveler")
public class TravelerResource {
    @GetMapping("/ping")
    public String ping(){
        return "ping";
    }
}
