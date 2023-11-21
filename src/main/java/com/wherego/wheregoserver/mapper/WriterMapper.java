package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.writer.WriterDto;
import com.wherego.wheregoserver.dto.writer.WriterRegisterDto;
import com.wherego.wheregoserver.dto.writer.WriterUpdateDto;
import com.wherego.wheregoserver.repository.entity.Writer;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WriterMapper {

    @Mapping(target = "avatar", ignore = true)
    Writer toWriterIgnoreAvatarField(WriterRegisterDto writer);

    WriterDto toWriterDto(Writer writer);

}
