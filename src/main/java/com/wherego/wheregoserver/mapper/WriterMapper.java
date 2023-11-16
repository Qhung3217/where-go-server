package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.WriterRegisterDto;
import com.wherego.wheregoserver.repository.entity.Writer;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
@Component
public interface WriterMapper {

    Writer toWriter(WriterRegisterDto writer);
}
