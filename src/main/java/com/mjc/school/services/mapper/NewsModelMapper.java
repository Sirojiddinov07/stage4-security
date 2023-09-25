package com.mjc.school.services.mapper;

import com.mjc.school.model.NewsModel;
import com.mjc.school.services.dto.NewsRequestDTO;
import com.mjc.school.services.dto.NewsResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper
public interface NewsModelMapper {
    @Mapping(target = "tagsSet", source = "tags")
    @Mapping(target = "authorId", source = "author.id")
    NewsResponseDTO modelToDTO(NewsModel newsModel);
    List<NewsResponseDTO> modelListToDtoList (List<NewsModel> newsList);
    @Mappings(value = {@Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true),
            @Mapping(target = "author.id", source = "authorId"),
            @Mapping(target = "tags", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    NewsModel dtoToModel (NewsRequestDTO requestDTO);

}