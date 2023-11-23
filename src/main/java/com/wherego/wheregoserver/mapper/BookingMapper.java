package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.booking.CreateBookingDto;
import com.wherego.wheregoserver.repository.entity.Booking;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BookingMapper {

    @Mapping(target="checkin", source="checkIn")
    @Mapping(target="checkout", source="checkOut")
    Booking toBooking(CreateBookingDto createBookingDto);
}
