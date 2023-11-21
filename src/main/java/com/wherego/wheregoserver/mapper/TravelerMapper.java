package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.traveler.TravelerDto;
import com.wherego.wheregoserver.dto.traveler.TravelerRegisterDto;
import com.wherego.wheregoserver.repository.entity.Traveler;
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
public interface TravelerMapper {

    Traveler toTraveler(TravelerRegisterDto register);

    TravelerDto toTravelerDto(Traveler traveler);
}
