package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.booking.CreateBookingDto;
import com.wherego.wheregoserver.service.BookingService;
import com.wherego.wheregoserver.utils.HeaderUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/booking")
public class BookingResource {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseMessageDto> booking(
            @RequestHeader("Authorization") String authorization,
            @RequestBody CreateBookingDto booking
    ){
        String token = HeaderUtils.getToken(authorization);
        return new ResponseEntity<ResponseMessageDto>(
                bookingService.create(token, booking),
                HttpStatus.CREATED
        );
    }
}
