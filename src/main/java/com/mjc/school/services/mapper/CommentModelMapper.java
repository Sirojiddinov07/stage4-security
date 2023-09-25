package com.mjc.school.services.mapper;

import com.mjc.school.model.CommentModel;
import com.mjc.school.services.dto.CommentRequestDTO;
import com.mjc.school.services.dto.CommentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface CommentModelMapper {

    @Mapping(target = "newsId", source = "news.id")
    CommentResponseDTO modelToDTO(CommentModel commentModel);

    List<CommentResponseDTO> modelListToDtoList (List<CommentModel> commentList);

    @Mappings(value = {@Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true),
            @Mapping(target = "news", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    CommentModel dtoToModel (CommentRequestDTO requestDTO);
}
