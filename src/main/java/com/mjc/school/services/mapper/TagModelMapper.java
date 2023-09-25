package com.mjc.school.services.mapper;

import com.mjc.school.model.TagModel;
import com.mjc.school.services.dto.TagRequestDTO;
import com.mjc.school.services.dto.TagResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TagModelMapper {

    TagResponseDTO modelToDTO(TagModel model);

    List<TagResponseDTO> modelListToDtoList(List<TagModel> modelList);

    @Mapping(target = "news", ignore = true)
    @Mapping(target = "id", ignore = true)
    TagModel dtoToModel(TagRequestDTO dto);

}

