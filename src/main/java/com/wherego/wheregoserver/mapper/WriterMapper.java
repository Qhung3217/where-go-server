package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.article.DetailArticleNoAuthorDto;
import com.wherego.wheregoserver.dto.writer.DetailWriterDto;
import com.wherego.wheregoserver.dto.writer.WriterRegisterDto;
import com.wherego.wheregoserver.repository.entity.Article;
import com.wherego.wheregoserver.repository.entity.Writer;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WriterMapper {

    @Mapping(target = "avatar", ignore = true)
    Writer toWriterIgnoreAvatarField(WriterRegisterDto writer);

    @Mapping(target = "articles", source = "articles", qualifiedByName = "getDetailArticleNoAuthor")
    DetailWriterDto toDetailWriterDto(Writer writer);

    @Named("getDetailArticleNoAuthor")
    static Set<DetailArticleNoAuthorDto> getDetailArticleNoAuthor(List<Article> articles){
        Set<DetailArticleNoAuthorDto> result = new HashSet<>();
        for (Article article : articles){
            DetailArticleNoAuthorDto detailArticle = new DetailArticleNoAuthorDto();
            detailArticle.setId(article.getId());
            detailArticle.setTitle(article.getTitle());
            detailArticle.setContent(article.getContent());
            detailArticle.setThumbnail(article.getThumbnail());
            detailArticle.setCreateDate(article.getCreateDate());
            detailArticle.setShortDescription(article.getShortDescription());
            result.add(detailArticle);
        }
        return result;
    }

}
