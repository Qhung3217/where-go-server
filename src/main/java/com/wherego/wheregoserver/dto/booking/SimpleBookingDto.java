package com.wherego.wheregoserver.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleBookingDto {
    private Long id;
    private Date bookDate;
    private Long price;
    private int people;
    private Date checkin;
    private Date checkout;
    private String hotelName;
}
