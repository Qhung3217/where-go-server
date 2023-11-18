package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import com.wherego.wheregoserver.dto.writer.SimpleWriterDto;
import com.wherego.wheregoserver.repository.entity.Article;
import com.wherego.wheregoserver.repository.entity.Writer;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, collectionMappingStrategy =
        CollectionMappingStrategy.ADDER_PREFERRED)
@Component
public interface ArticleMapper {

    @Mapping(target="writer", source="writer", qualifiedByName = "toSimpleWriter")
    SimpleArticleDto toSimpleArticleDto(Article article);

    @Named("toSimpleWriter")
    public static SimpleWriterDto getSimpleArticle(Writer writer){
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
