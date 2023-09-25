package com.mjc.school.services;

import com.mjc.school.services.dto.AuthorRequestDTO;
import com.mjc.school.services.dto.AuthorResponseDTO;

public interface AuthorService extends BaseService<AuthorRequestDTO, AuthorResponseDTO, Long> {
    AuthorResponseDTO getAuthorByNewsId(Long newsId);
}

