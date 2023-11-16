package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.writer.WriterDto;
import com.wherego.wheregoserver.dto.writer.WriterRegisterDto;
import com.wherego.wheregoserver.repository.entity.Writer;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WriterMapper {

    Writer toWriter(WriterRegisterDto writer);
    WriterRegisterDto toWriterRegisterDto(Writer writer);
    WriterDto toWriterDto(Writer writer);

}
