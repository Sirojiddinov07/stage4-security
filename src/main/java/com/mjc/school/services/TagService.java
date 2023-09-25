package com.mjc.school.services;

import com.mjc.school.services.dto.TagRequestDTO;
import com.mjc.school.services.dto.TagResponseDTO;

import java.util.List;

public interface TagService extends BaseService<TagRequestDTO, TagResponseDTO, Long>{
    List<TagResponseDTO> getTagsByNewsId(Long newsId);
}
