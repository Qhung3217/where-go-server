package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.article.CreateArticleDto;
import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import com.wherego.wheregoserver.dto.writer.SimpleWriterDto;
import com.wherego.wheregoserver.repository.entity.Article;
import com.wherego.wheregoserver.repository.entity.Writer;
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
public interface ArticleMapper {

    @Mapping(target="writer", source="writer", qualifiedByName = "toSimpleWriter")
    SimpleArticleDto toSimpleArticleDto(Article article);

    @Mapping(target="thumbnail", ignore = true)
    Article toArticle(CreateArticleDto article);

    @Named("toSimpleWriter")
    static SimpleWriterDto getSimpleArticle(Writer writer){
        return SimpleWriterDto
                .builder()
                .name(writer.getName())
                .dob(writer.getDob())
                .avatar(writer.getAvatar())
                .username(writer.getUsername())
                .tels(writer.getTels())
                .build();
    }
}
