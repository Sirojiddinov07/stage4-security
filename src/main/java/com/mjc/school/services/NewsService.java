package com.mjc.school.services;

import com.mjc.school.services.dto.NewsRequestDTO;
import com.mjc.school.services.dto.NewsResponseDTO;

import java.util.List;

public interface NewsService extends BaseService<NewsRequestDTO, NewsResponseDTO, Long>{
    List<NewsResponseDTO> readByParams(Long tagId, String tagName, String authorName, String title, String content);
}

