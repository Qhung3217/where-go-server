package com.wherego.wheregoserver.dto.booking;

import com.wherego.wheregoserver.repository.entity.Hotel;
import com.wherego.wheregoserver.repository.entity.Traveler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingDto {
    private Long hotelId;
    private Long price;
    private int people;
    private Date checkIn;
    private Date checkOut;
}
